package com.example.edgoo.numberguess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


            guessBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    smallLarger();
                    winOrLose();
                    guessLeft();
                }});

            newGameBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newGame();
                }
            });

        }

    public void winOrLose() {
        if (guessLeft == 0) {
            info.setText("Game Over... You Lose.\nThe random number was " + randomNumber);
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
                info.setText("CORRECT.. YOU WIN!! \n The random number was " + randomNumber);
            }
        }

        public void guessLeft(){
            guessLeftView.setText("You have " + guessLeft + " guesses left");
        }

        public void newGame(){
        randomNumber = (int) (Math.random() * 100) + 1;
        guessLeft = 10;
        info.setText("");
        guessLeftView.setText("");
        highLow.setText("");
        }

//        todo: make new levels.
//        todo: make better win or lose message.

    }

