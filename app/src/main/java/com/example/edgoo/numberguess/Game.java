package com.example.edgoo.numberguess;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Game extends MainActivity {

    EditText userGuess;
    TextView info;
    TextView guessLeftView;
    int guessLeft = 10;
    TextView highLow;
    Button guessBtn;
    int randomNumber = (int) (Math.random() * 100) + 1;

    public Game() {

        userGuess = findViewById(R.id.guess);
        info = findViewById(R.id.info);
        guessBtn = findViewById(R.id.guessButton);
        guessLeftView = findViewById(R.id.guessLeft);
        highLow = findViewById(R.id.highLow);


        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smallLarger();
                winOrLose();
                guessLeft();
            }});
    }
    public void winOrLose() {
        if (guessLeft == 0) {
            info.setText("Game Over... You Lose. The random number was " + randomNumber);
        }
    }

    //    method for larger or smaller
    public void smallLarger() {
        guessLeft = guessLeft - 1;

        String value = userGuess.getText().toString();
        int guess = Integer.parseInt(value);

        if (randomNumber < guess) {
            highLow.setText("It's smaller than " + guess + ".");
        } else if (randomNumber > guess) {
            highLow.setText("It's larger than " + guess + ".");
        } else {
            info.setText("CORRECT.. YOU WIN!! The random number was " + randomNumber);
        }
    }

    public void guessLeft(){
        guessLeftView.setText("You have " + guessLeft + " guesses left");
    }

    public void newGame(){
//        TODO: make new game button.
    }

//        todo: make new levels.
//        todo: make better win or lose message.

}
