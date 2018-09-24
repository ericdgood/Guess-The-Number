package com.example.edgoo.numberguess;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NextLevel extends AppCompatActivity {

        EditText userGuess;
        TextView info;
        TextView guessLeftView;
        int guessLeft = 10;
        TextView highLow;
        Button guessBtn;
        Button newGameBtn;
        Button nextLevelBtn;
        int randomNumber = (int) (Math.random() * 500) + 1;

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

//          GUESS BUTTON
            guessBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = userGuess.getText().toString();

                    if (value.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please enter a numerical guess", Toast.LENGTH_SHORT).show();;
                    } else {
                        smallLarger();
                        guessLeft();
                        userGuess.getText().clear();
                        userGuess.setHint(R.string.next_guess);
                    }}
            });

//          NEW GAME BUTTONG
            newGameBtn.setVisibility(View.GONE);
            newGameBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newGame();
                }
            });

            nextLevelBtn.setVisibility(View.GONE);
            nextLevelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NextLevel.class);
//                startActivity(intent);

                }
            });

//            KEEP KEYBOARD OPEN TO KEYPAD AND MAKE DONE BTN SUBMIT ANSWER
            userGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                        String value = userGuess.getText().toString();

                        if (value.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Please enter a numerical guess", Toast.LENGTH_SHORT).show();
                        } else {
                            smallLarger();
                            guessLeft();
                            userGuess.getText().clear();
                            userGuess.setHint(R.string.next_guess);
                        }
                    }
                    return guessLeft != 0;
                }
            });

        }

        //    method for larger or smaller / WIN OR LOSE
        @SuppressLint("SetTextI18n")
        public void smallLarger() {

            guessLeft = guessLeft - 1;

            if (guessLeft >= 0) {
                String value = userGuess.getText().toString();
                int guess = Integer.parseInt(value);

                if (randomNumber < guess) {
                    highLow.setText("It's smaller than " + guess + "." + randomNumber);
                } else if (randomNumber > guess) {
                    highLow.setText("It's larger than " + guess + ".");
                } else {
                    info.setText("CORRECT.. YOU WIN!! \n The random number was " + randomNumber);
                    nextLevelBtn.setVisibility(View.VISIBLE);
                    guessBtn.setVisibility(View.GONE);
                }
            } else {
                info.setText("Game Over... \n The random number was " + randomNumber);
                newGameBtn.setVisibility(View.VISIBLE);
                guessBtn.setVisibility(View.GONE);
            }
        }

        @SuppressLint("SetTextI18n")
        public void guessLeft() {
            guessLeftView.setText("You have " + guessLeft + " guesses left");
        }

        public void newGame() {
            randomNumber = (int) (Math.random() * 100) + 1;
            guessLeft = 10;
            info.setText("");
            guessLeftView.setText("");
            highLow.setText("");
            newGameBtn.setVisibility(View.GONE);
            guessBtn.setVisibility(View.VISIBLE);
            userGuess.setHint(R.string.guess_here);
        }

//        todo: make new levels.
//        todo: make better win or lose message.

    }
