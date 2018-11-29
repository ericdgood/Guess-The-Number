package com.example.edgoo.numberguess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.edgoo.numberguess.RoomData.GameActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main_Activity extends AppCompatActivity {

    @BindView(R.id.high_level)
    TextView highLevel;
    @BindView(R.id.high_level_range)
    TextView highLevelRange;
    @BindView(R.id.new_game_btn)
    Button newGameBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

//        SETS ACTIONBAR BLANK AND REMOVES SHADOW
        setTitle("");
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);


        highLevel.setText("8");
        highLevelRange.setText("1,000,000");

//      BTN TO START NEW GAME
        newGameBtn.setOnClickListener(v ->  {

        Intent gameActivity = new Intent(this, GameActivity.class);
        startActivity(gameActivity);

        });
    }
}
