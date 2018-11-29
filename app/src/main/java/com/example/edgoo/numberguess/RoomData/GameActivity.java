package com.example.edgoo.numberguess.RoomData;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edgoo.numberguess.R;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "logtag";
    @BindView(R.id.level_title)
    TextView levelTitle;
    @BindView(R.id.guessesLeft)
    TextView guessesLeft;
    @BindView(R.id.guess_range)
    TextView guessange;
    @BindView(R.id.guessEditText)
    EditText UserGuessEditText;
    @BindView(R.id.guessBtn)
    Button guessBtn;
    @BindView(R.id.hint_layout)
    LinearLayout hintLayout;
    @BindView(R.id.hint_arrow)
    ImageView hintArrow;

    int userGuess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        ButterKnife.bind(this);

//        SETS ACTIONBAR BLANK AND REMOVES SHADOW
        setTitle("");
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

//        EXAMPLE VIEW CODE. TODO: MAKE THIS FILL WITH DATA
        levelTitle.setText("Level 1");
        guessesLeft.setText("You have 10 Guesses.");
        guessange.setText("1,000,000");

//        GETS RANDOM NUMBER todo: make it get random number per level.
        int randomNumber = getRandomNumber();

//        GETS USERS GUES. TODO: check if the guess is in range and numbers only
        guessBtn.setOnClickListener(v -> {
            hintDisplay();
            getUserGuess();
            checkUserGuess(userGuess, randomNumber);
        });

    }

    public Integer getRandomNumber (){
        return new Random().nextInt(10) + 1;
    }


    public void checkUserGuess(int userGuess, int randomNumber){
        if (userGuess == randomNumber){
            hintArrow.setColorFilter(getResources().getColor(R.color.btn));
        }
    }

    public void getUserGuess (){
        userGuess = Integer.parseInt(UserGuessEditText.getText().toString());
    }

//    SHOWS HINT DISPLAY AFTER GUESS TODO: arrows, shows guess
    public void hintDisplay(){
        hintArrow.setColorFilter(getResources().getColor(R.color.low));
        hintLayout.setVisibility(View.VISIBLE);
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
