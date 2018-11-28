package com.example.edgoo.numberguess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    int level = 1;
    TextView levelInfo;
    LinearLayout popUpMessage;
    int randomNumber = (int) (Math.random() * 10) + 1;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_activity);


//        userGuess = findViewById(R.id.guess);
//        info = findViewById(R.id.info);
//        guessBtn = findViewById(R.id.guessButton);
//        guessLeftView = findViewById(R.id.guessLeft);
//        highLow = findViewById(R.id.highLow);
//        newGameBtn = findViewById(R.id.new_game_btn);
//        nextLevelBtn = findViewById(R.id.next_level_btn);
//        popUpMessage = findViewById(R.id.pop_up_message);
//        levelInfo = findViewById(R.id.level_info);
//
//
//        guessBtn();
//        doneBtn();
//        guessLeft();
//
//        setTitle(R.string.level_1);
    }
//
//    //    method for larger or smaller / WIN OR LOSE
//    public void smallLarger() {
//
//        guessLeft = guessLeft - 1;
//
//        if (guessLeft > 0) {
//            String value = userGuess.getText().toString();
//            int guess = Integer.parseInt(value);
//
//            if (guess > randomNumber) {
//                highGuess();
//            } else if (guess < randomNumber) {
//                lowGuess();
//            } else {
//                winMethod();
//            }
//        } else {
//            loseMethod();
//        }
//    }
//
//    //    WHAT HAPPENS IF USER WINS
//    public void winMethod() {
//        popUpMessage.setVisibility(View.VISIBLE);
//        info.setText("CORRECT.. YOU WIN!!  \n The random number was " + randomNumber);
//        newGameBtn.setVisibility(View.GONE);
//        nextLevelBtn.setVisibility(View.VISIBLE);
//        nextLevelBtn();
//    }
//
//    //    WHAT HAPPENS IF USER LOSES
//    public void loseMethod() {
//        popUpMessage.setVisibility(View.VISIBLE);
//        info.setText("Game Over... \n The random number was " + randomNumber);
//        newGameBtn.setVisibility(View.VISIBLE);
//        nextLevelBtn.setVisibility(View.GONE);
//        newGameBtn();
//    }
//
//    //    IF GUESS IS TO HIGH
//    public void highGuess() {
//        String value = userGuess.getText().toString();
//        int guess = Integer.parseInt(value);
//        highLow.setBackgroundResource(R.color.high);
//        highLow.setText("It's smaller than " + guess + ".");
//    }
//
//    //    IF GUESS IS TO SMALL
//    public void lowGuess() {
//        String value = userGuess.getText().toString();
//        int guess = Integer.parseInt(value);
//        highLow.setBackgroundResource(R.color.low);
//        highLow.setText("It's larger than " + guess + ".");
//    }
//
//    //    GUESSES LEFT MESSAGE
//    public void guessLeft() {
//
//        guessLeftView.setText("You have " + guessLeft + " guesses left");
//    }
//
//    //    NEW GAME BUTTON
//    public void newGameBtn() {
//        newGameBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                randomNumber = (int) (Math.random() * 10) + 1;
//                guessLeft = 10;
//                guessLeft();
//                highLow.setText("");
//                guessBtn.setVisibility(View.VISIBLE);
//                userGuess.setHint(R.string.guess_here);
//                popUpMessage.setVisibility(View.GONE);
//                highLow.setBackgroundResource(R.color.none);
//                setTitle(R.string.level_1);
//                levelInfo.setText(R.string.level_1_info);
//            }
//        });
//    }
//
//    public void newGameMenu(){
//        randomNumber = (int) (Math.random() * 10) + 1;
//        guessLeft = 10;
//        guessLeft();
//        highLow.setText("");
//        guessBtn.setVisibility(View.VISIBLE);
//        userGuess.setHint(R.string.guess_here);
//        popUpMessage.setVisibility(View.GONE);
//        highLow.setBackgroundResource(R.color.none);
//        setTitle(R.string.level_1);
//        levelInfo.setText(R.string.level_1_info);
//    }
//
//    //    GUESS BUTTON
//    public void guessBtn() {
//        nextLevelBtn.setVisibility(View.GONE);
//        popUpMessage.setVisibility(View.GONE);
//        newGameBtn.setVisibility(View.GONE);
//        guessBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                guessEntered();
//            }
//        });
//    }
//
//    //    KEEP KEYBOARD OPEN TO KEYPAD AND MAKE DONE BTN SUBMIT ANSWER
//    public void doneBtn() {
//        userGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
//                    guessEntered();
//                }
//                return guessLeft != 0;
//            }
//        });
//    }
//
//    //    NEXT LEVEL BUTTON
//    public void nextLevelBtn() {
//        nextLevelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                level = level + 1;
//                switch (level) {
//                    case 2:
//                        level2();
//                        break;
//                    case 3:
//                        level3();
//                        break;
//                    case 4:
//                        level4();
//                        break;
//                    case 5:
//                        level5();
//                        break;
//                    case 6:
//                        level6();
//                        break;
//                    case 7:
//                        level7();
//                        break;
//                    case 8:
//                        level8();
//                        break;
//                    default:
//                }
//            }
//        });
//    }
//
//    //    WHEN A USER ENTERS A NUMBER
//    public void guessEntered() {
//        String value = userGuess.getText().toString();
//
//        if (value.isEmpty()) {
//            Toast.makeText(getApplicationContext(), "Please enter a numerical guess", Toast.LENGTH_SHORT).show();
//            ;
//        } else {
//            smallLarger();
//            guessLeft();
//            userGuess.getText().clear();
//            userGuess.setHint(R.string.next_guess);
//        }
//    }
//
//    //    RESETS ITEMS FOR NEXT LEVEL
//    public void nextLevelReset() {
//        guessLeft = guessLeft + 10;
//        guessLeft();
//        highLow.setBackgroundResource(R.color.none);
//        info.setText("");
//        highLow.setText("");
//        guessBtn.setVisibility(View.VISIBLE);
//        userGuess.setHint(R.string.guess_here);
//        popUpMessage.setVisibility(View.GONE);
//    }
//
//    //        LEVEL 2
//    public void level2() {
//        nextLevelReset();
//        randomNumber = (int) (Math.random() * 50) + 1;
//        levelInfo.setText(R.string.level_2_info);
//        setTitle(R.string.level_2);
//    }
//
//    //        LEVEL 3
//    public void level3() {
//        randomNumber = (int) (Math.random() * 100) + 1;
//        nextLevelReset();
//        levelInfo.setText(R.string.level_3_info);
//        setTitle(R.string.level_3);
//    }
//
//    //        LEVEL 4
//    public void level4() {
//        randomNumber = (int) (Math.random() * 500) + 1;
//        nextLevelReset();
//        levelInfo.setText(R.string.level_4_info);
//        setTitle(R.string.level_4);
//    }
//
//    //        LEVEL 5
//    public void level5() {
//        randomNumber = (int) (Math.random() * 1000) + 1;
//        nextLevelReset();
//        levelInfo.setText(R.string.level_5_info);
//        setTitle(R.string.level_5);
//    }
//
//    //        LEVEL 6
//    public void level6() {
//        randomNumber = (int) (Math.random() * 5000) + 1;
//        nextLevelReset();
//        levelInfo.setText(R.string.level_6_info);
//        setTitle(R.string.level_6);
//    }
//
//    //        LEVEL 7
//    public void level7() {
//        randomNumber = (int) (Math.random() * 10000) + 1;
//        nextLevelReset();
//        levelInfo.setText(R.string.level_7_info);
//        setTitle(R.string.level_7);
//    }
//
//    //        LEVEL 8
//    public void level8() {
//        randomNumber = (int) (Math.random() * 50000) + 1;
//        nextLevelReset();
//        levelInfo.setText(R.string.level_8_info);
//        setTitle(R.string.level_8);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.newgamemenu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        MenuItem newgame = menu.findItem(R.id.new_game);
//        newgame.setVisible(true);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.new_game:
//                newGameMenu();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}

