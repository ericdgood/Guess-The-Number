package com.numberguess.edgoo.numberguess;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.numberguess.edgoo.numberguess.HighScore.FirebaseHighScore;
import com.numberguess.edgoo.numberguess.RoomData.AppDatabase;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main_Activity extends AppCompatActivity {

    private static final String TAG = "test";
    @BindView(R.id.high_level)
    TextView highLevel;
    @BindView(R.id.high_level_range)
    TextView highLevelRange;
    @BindView(R.id.high_level_range_linear)
    LinearLayout highlevelRangeLayout;
    @BindView(R.id.new_game_btn)
    Button newGameBtn;
    @BindView(R.id.level_label)
    TextView levelLabel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

//        SETS ACTIONBAR BLANK AND REMOVES SHADOW
        setTitle("");
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);


        gethighscore();

//      BTN TO START NEW GAME
        newGameBtn.setOnClickListener(v ->  {

        Intent gameActivity = new Intent(this, GameActivity.class);
        startActivity(gameActivity);
        finish();
        });
    }

    //    GET HIGH SCORE
    public void gethighscore(){
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "number_guess")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        int highLevelData = db.numberGuessDao().gethighLevel();
        int highRangeData = db.numberGuessDao().gethighRange();
        String highRangeDataString =  GameActivity.getFormatedAmount(highRangeData);
        if (highLevelData == 0){
            levelLabel.setVisibility(View.GONE);
            highlevelRangeLayout.setVisibility(View.GONE);
            highLevel.setText(R.string.no_record);
        } else {
            highLevel.setText(String.valueOf(highLevelData));
            highLevelRange.setText(highRangeDataString);
        }
    }

    //    OPTION FOR NEW GAME
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.highscore, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.high_score:
                Intent highScore = new Intent(this, FirebaseHighScore.class);
                startActivity(highScore);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
