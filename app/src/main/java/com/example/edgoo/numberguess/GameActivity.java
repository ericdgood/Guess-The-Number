package com.example.edgoo.numberguess;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import com.example.edgoo.numberguess.Fragments.CorrectGameFragment;
import com.example.edgoo.numberguess.Fragments.LoserGameFragment;
import com.example.edgoo.numberguess.R;

import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "logtag";
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
    int level = 1;
    int randomNumber;
    int levelMaxRange;
    int guessesLeft = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        ButterKnife.bind(this);

//        SETS ACTIONBAR BLANK AND REMOVES SHADOW
        setTitle("");
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        levelTitle.setText(R.string.level + level);

        String guessesLeftString = ("You have " + String.valueOf(guessesLeft) + " guesses.");
        guessesLeftView.setText(guessesLeftString);

        setLevelMaxRange();
        randomNumber = getRandomNumber();

//        GETS USERS GUESS
        doneBtn();
        guessBtn.setOnClickListener(v -> {
            onGuess();
        });
    }

    public void onGuess(){
        getUserGuess();
        checkUserGuess(userGuess, randomNumber);
        hintDisplay();
        guessesLeft();
        UserGuessEditText.getText().clear();
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

        guessange.setText(String.valueOf(levelMaxRange));
    }

    public Integer getRandomNumber() {
        return new Random().nextInt(levelMaxRange - 1) + 1;
    }

    public void guessesLeft() {
        guessesLeft--;
        if (guessesLeft > 0) {
            String guessesLeftString = ("You have " + String.valueOf(guessesLeft) + " guesses.");
            guessesLeftView.setText(guessesLeftString);
        } else {
//            DO THIS IF OUT OF GUESSES OR LOSE
            gameFragmentLayout.setVisibility(View.VISIBLE);
            LoserGameFragment LoseGameFragment = new LoserGameFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.game_fragment, LoseGameFragment)
                    .commit();
        }
    }

    public void checkUserGuess(int userGuess, int randomNumber) {
        if (userGuess == randomNumber) {
            gameFragmentLayout.setVisibility(View.VISIBLE);

            hideKeybord();
            CorrectGameFragment correctGameFragment = new CorrectGameFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
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

    public void getUserGuess() {
        Boolean userGuessDigitOnly = TextUtils.isDigitsOnly(UserGuessEditText.getText().toString());
        String userGuessString = (UserGuessEditText.getText().toString());

        if (!userGuessDigitOnly || userGuessString.equals("")) {
//           DO THIS IF USER ENTERS LETTERS OR ENTERS NO VALUE
            Toast.makeText(this, "Enter your guess", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(userGuessString) > levelMaxRange) {
//           DO THIS IF GUESS IS TO LARGE
            Toast.makeText(this, "Enter a value under " + levelMaxRange, Toast.LENGTH_SHORT).show();
        } else {
            userGuess = Integer.parseInt(userGuessString);
        }
    }

    //    SHOWS HINT DISPLAY AFTER GUESS
    public void hintDisplay() {
        hintLayout.setVisibility(View.VISIBLE);
        String largeThan = (getString(R.string.large_than) + " " + userGuess);
        String smallerThan = (getString(R.string.smaller_than) + " " + userGuess);

        if (userGuess < randomNumber) {
//            DO THIS IS GUESS IS SMALLER THAN NUMBER
            hintArrow.setImageResource(R.drawable.ic_arrow_upward);
            hintArrow.setColorFilter(getResources().getColor(R.color.high));
            hintMessage.setText(largeThan);
        } else {
//            DO THIS IS GUESS IS SMALLER THAN NUMBER
            hintArrow.setImageResource(R.drawable.ic_arrow_downward);
            hintArrow.setColorFilter(getResources().getColor(R.color.low));
            hintMessage.setText(smallerThan);
        }
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
//                newGameMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
