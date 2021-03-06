package com.numberguess.edgoo.numberguess;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.numberguess.edgoo.numberguess.Fragments.CorrectGameFragment;
import com.numberguess.edgoo.numberguess.Fragments.LosingGameFragment;
import com.numberguess.edgoo.numberguess.HighScore.FirebaseHighScore;
import com.numberguess.edgoo.numberguess.RoomData.AppDatabase;
import com.numberguess.edgoo.numberguess.RoomData.NumberGuessData;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.numberguess.edgoo.numberguess.HighScore.FirebaseHighScore.mUsername;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "test";
    @BindView(R.id.level_title)
    TextView levelTitle;
    @BindView(R.id.guessesLeft)
    TextView guessesLeftView;
    @BindView(R.id.guess_range)
    TextView guessange;
    @BindView(R.id.guessEditText)
    EditText UserGuessEditText;
    @BindView(R.id.guessBtn)
    Button guessBtn;
    @BindView(R.id.hint_layout)
    LinearLayout hintLayout;
    @BindView(R.id.hint)
    TextView hintMessage;
    @BindView(R.id.hint_arrow)
    ImageView hintArrow;
    @BindView(R.id.game_fragment)
    FrameLayout gameFragmentLayout;

    int userGuess;
    int level;
    int randomNumber;
    int levelMaxRange;
    int guessesLeft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        ButterKnife.bind(this);

        getInfoForNewLevel();
        setLevelInfo();
        setLevelMaxRange();
        getRandomNumber();

//        SHOWS RANDOM NUMBER FOR TESTING
        Log.i(TAG, "random number: " + randomNumber);

//        GETS USERS GUESS
        doneBtn();
        guessBtn.setOnClickListener(v -> {
            onGuess();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("guesses_left", guessesLeft);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        guessesLeft = (int) savedInstanceState.get("guesses_left");
    }

    public void onGuess(){
        if (getUserGuess()) {
            getUserGuess();
            checkUserGuess(userGuess, randomNumber);
            hintDisplay();
            UserGuessEditText.getText().clear();
            UserGuessEditText.setHint("Next Guess");
        }
    }

    public void setLevelMaxRange() {
        if (level == 1) {
            levelMaxRange = 10;
        } else if ((level % 2) == 0) {
//            DO THIS IS LEVEL IS EVEN
            levelMaxRange = levelMaxRange * 5;
        } else {
//            DO THIS IS LEVEL IS ODD
            levelMaxRange = levelMaxRange * 2;
        }
        String levelMaxRangeString = getFormatedAmount(levelMaxRange);
        guessange.setText(levelMaxRangeString);
    }

    public void getRandomNumber() {
        randomNumber = new Random().nextInt(levelMaxRange - 1) + 1;
    }

//      CHECKS NUMBER OF GUESSES AND SHOWS LOSING FRAG IF NO GUESSES LEFT
    public void guessesLeft() {
        guessesLeft--;
        if (guessesLeft > 0) {
            String guessesLeftString = ("You have " + String.valueOf(guessesLeft) + " guesses.");
            guessesLeftView.setText(guessesLeftString);
        } else {
//            DO THIS IF OUT OF GUESSES OR LOSE
            gameFragmentLayout.setVisibility(View.VISIBLE);
            hideKeybord();
            LosingGameFragment LoseGameFragment = new LosingGameFragment();
            LoseGameFragment.getLosingFragInfo(this, randomNumber);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.game_fragment, LoseGameFragment)
                    .commit();
        }
    }

//      CHECKS ANSWER AND SHOWS CORRECT FRAG IF CORRECT
    public void checkUserGuess(int userGuess, int randomNumber) {
        guessesLeft();
        if (userGuess == randomNumber) {
            gameFragmentLayout.setVisibility(View.VISIBLE);
            savehighscore();
            level++;
            hideKeybord();

            CorrectGameFragment correctGameFragment = new CorrectGameFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            correctGameFragment.getCorrectFragInfo(this, level, levelMaxRange, guessesLeft);
            fragmentManager.beginTransaction()
                    .replace(R.id.game_fragment, correctGameFragment)
                    .commit();
        }
    }

        //    KEEP KEYBOARD OPEN TO KEYPAD AND MAKE DONE BTN SUBMIT ANSWER
    public void doneBtn() {
        UserGuessEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    onGuess();
                    UserGuessEditText.getText().clear();
                }
                return guessesLeft != 0;
            }
        });
    }

    public void hideKeybord() {
        // hide virtual keyboard
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        assert inputManager != null;
        inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public Boolean getUserGuess() {
        Boolean userGuessDigitOnly = TextUtils.isDigitsOnly(UserGuessEditText.getText().toString());
        String userGuessString = (UserGuessEditText.getText().toString());

        if (!userGuessDigitOnly || userGuessString.equals("")) {
//           DO THIS IF USER ENTERS LETTERS OR ENTERS NO VALUE
            Toast.makeText(this, "Enter your guess", Toast.LENGTH_SHORT).show();
            return false;
        } else if (Integer.parseInt(userGuessString) > levelMaxRange) {
//           DO THIS IF GUESS IS TO LARGE
            Toast.makeText(this, "Enter a value under " + levelMaxRange, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            userGuess = Integer.parseInt(userGuessString);
        }
        return true;
    }

    //    SHOWS HINT DISPLAY AFTER GUESS
    public void hintDisplay() {
        hintLayout.setVisibility(View.VISIBLE);
        String largeThan = (getString(R.string.large_than) + " " + userGuess);
        String smallerThan = (getString(R.string.smaller_than) + " " + userGuess);

        if (userGuess < randomNumber) {
//            DO THIS IS GUESS IS SMALLER THAN NUMBER
            hintArrow.setImageResource(R.drawable.ic_arrow_upward);
            hintArrow.setColorFilter(getResources().getColor(R.color.low));
            hintMessage.setText(largeThan);
        } else {
//            DO THIS IS GUESS IS SMALLER THAN NUMBER
            hintArrow.setImageResource(R.drawable.ic_arrow_downward);
            hintArrow.setColorFilter(getResources().getColor(R.color.high));
            hintMessage.setText(smallerThan);
        }
    }

    public void getInfoForNewLevel(){
        level = getIntent().getIntExtra("level", 1);
        levelMaxRange = getIntent().getIntExtra("levelMax", 10);
        guessesLeft = getIntent().getIntExtra("guessesLeft", 0);
    }

    public void setLevelInfo(){
//        SETS ACTIONBAR BLANK AND REMOVES SHADOW
        setTitle("");
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
//        SET LEVEL TITLE
        String levelTitleString = (getString(R.string.level) + " " + String.valueOf(level));
        levelTitle.setText(levelTitleString);
//      todo: underline guessesleft
        guessesLeft = guessesLeft + 10;
        String guessesLeftString = ("You have " + String.valueOf(guessesLeft) + " guesses.");
        guessesLeftView.setText(guessesLeftString);
    }

    //    OPTION FOR NEW GAME
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newgamemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                Intent returnHome = new Intent(this, Main_Activity.class);
                startActivity(returnHome);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    SAVE HIGH SCORE
    public void savehighscore(){
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "number_guess")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

            String User = FirebaseHighScore.mUsername;
            NumberGuessData highScore = new NumberGuessData(level, levelMaxRange, User);
            db.numberGuessDao().insertAll(highScore);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("are you sure you want to quit?")
                .setPositiveButton("quit", (dialog, which) -> {
                    Intent returnHome = new Intent(getBaseContext(), Main_Activity.class);
                    startActivity(returnHome);
                    finish();
                })
                .setNegativeButton("No", null)
                .show();
    }

    public static String getFormatedAmount(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }
}
