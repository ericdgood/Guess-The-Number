package com.example.edgoo.numberguess;

import android.os.Bundle;
import android.view.View;

public class Level2 extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popUpMessage = findViewById(R.id.pop_up_message);

        popUpMessage.setVisibility(View.GONE);
        randomNumber = (int) (Math.random() * 100) + 1;
        guessLeft = 10;
        info.setText("");
        guessLeftView.setText("");
        highLow.setText("");
        newGameBtn.setVisibility(View.GONE);
        guessBtn.setVisibility(View.VISIBLE);
        userGuess.setHint(R.string.guess_here);
//        game();


    }
}
