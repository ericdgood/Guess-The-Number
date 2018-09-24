package com.example.edgoo.numberguess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText userGuess;
    TextView info;
    TextView guessLeftView;
    int guessLeft = 10;
    TextView highLow;
    Button guessBtn;
    Button newGameBtn;
    Button nextLevelBtn;
    LinearLayout popUpMessage;
    int randomNumber = (int) (Math.random() * 100) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userGuess = findViewById(R.id.guess);
        info = findViewById(R.id.info);
        guessBtn = findViewById(R.id.guessButton);
        guessLeftView = findViewById(R.id.guessLeft);
        highLow = findViewById(R.id.highLow);
        newGameBtn = findViewById(R.id.new_game_btn);
        nextLevelBtn = findViewById(R.id.next_level_btn);
        popUpMessage = findViewById(R.id.pop_up_message);


        guessBtn();
        newGameBtn();
        doneBtn();
        nextLevelBtn();

    }

//    method for larger or smaller / WIN OR LOSE
    public void smallLarger() {

        guessLeft = guessLeft - 1;

        if (guessLeft >= 0) {
            String value = userGuess.getText().toString();
            int guess = Integer.parseInt(value);

            if (guess > randomNumber) {
                highGuess();
            } else if (guess < randomNumber) {
                lowGuess();
            } else {
                winMethod();
            }
        } else {
            loseMethod();
        }
    }

//    WHAT HAPPENS IF USER WINS
    public void winMethod(){
        popUpMessage.setVisibility(View.VISIBLE);
        info.setText("CORRECT.. YOU WIN!!  \n The random number was " + randomNumber);
//        nextLevelBtn.setVisibility(View.VISIBLE);
        newGameBtn.setVisibility(View.VISIBLE);
    }

//    WHAT HAPPENS IF USER LOSES
    public void loseMethod(){
        popUpMessage.setVisibility(View.VISIBLE);
        info.setText("Game Over... \n The random number was " + randomNumber);
        newGameBtn.setVisibility(View.VISIBLE);
    }

//    IF GUESS IS TO HIGH
    public void highGuess(){
        String value = userGuess.getText().toString();
        int guess = Integer.parseInt(value);

        highLow.setText("It's smaller than " + guess + ".");
    }

//    IF GUESS IS TO SMALL
    public void lowGuess(){
        String value = userGuess.getText().toString();
        int guess = Integer.parseInt(value);

        highLow.setText("It's larger than " + guess + "." + randomNumber);
    }

//    GUESSES LEFT MESSAGE
    public void guessLeft() {

        guessLeftView.setText("You have " + guessLeft + " guesses left");
    }

//    NEW GAME BUTTON
    public void newGameBtn() {
        newGameBtn.setVisibility(View.GONE);
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        randomNumber = (int) (Math.random() * 100) + 1;
        guessLeft = 10;
        info.setText("");
        guessLeftView.setText("");
        highLow.setText("");
        newGameBtn.setVisibility(View.GONE);
        guessBtn.setVisibility(View.VISIBLE);
        userGuess.setHint(R.string.guess_here);
        popUpMessage.setVisibility(View.GONE);
            }
        });
    }

//    GUESS BUTTON
    public void guessBtn(){
        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessEntered();
        }
    });
    }

//    KEEP KEYBOARD OPEN TO KEYPAD AND MAKE DONE BTN SUBMIT ANSWER
    public void doneBtn(){
        userGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    guessEntered();
                }
                return guessLeft != 0;
            }
        });
    }

//    NEXT LEVEL BUTTON
    public void nextLevelBtn(){
        nextLevelBtn.setVisibility(View.GONE);
        popUpMessage.setVisibility(View.GONE);
        nextLevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NextLevel.class);
//                startActivity(intent);

            }
        });
    }

//    WHEN A USER ENTERS A NUMBER
    public void guessEntered(){
        String value = userGuess.getText().toString();

        if (value.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a numerical guess", Toast.LENGTH_SHORT).show();;
        } else {
            smallLarger();
            guessLeft();
            userGuess.getText().clear();
            userGuess.setHint(R.string.next_guess);
        }}

}

