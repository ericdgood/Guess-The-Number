package com.numberguess.edgoo.numberguess.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.numberguess.edgoo.numberguess.GameActivity;
import com.numberguess.edgoo.numberguess.Main_Activity;
import com.numberguess.edgoo.numberguess.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CorrectGameFragment extends Fragment {

    @BindView(R.id.next_level_btn)
    Button nextLeveBtn;
    @BindView(R.id.guessesLeftMessage)
    TextView guessesLeftMessage;

    int level;
    int levelMaxRange;
    int guessesLeft;
    GameActivity gameActivity;

    public CorrectGameFragment() {
    }

    public void getCorrectFragInfo(GameActivity gameActivity, int level, int levelMaxRange, int guessesLeft){
        this.gameActivity = gameActivity;
        this.level = level;
        this.levelMaxRange = levelMaxRange;
        this.guessesLeft = guessesLeft;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Create view
        View rootView = inflater.inflate(R.layout.correct_game_fragment, container, false);
        ButterKnife.bind(this, rootView);

        String guessesLeftMessageString = "You had " + String.valueOf(guessesLeft) + " guesses left.\nUse them on the next level";
        guessesLeftMessage.setText(guessesLeftMessageString);

        nextLeveBtn.setOnClickListener( v -> {
            Intent nextLevel = new Intent(getActivity(), GameActivity.class);
            nextLevel.putExtra("level", level);
            nextLevel.putExtra("levelMax", levelMaxRange);
            nextLevel.putExtra("guessesLeft", guessesLeft);
            startActivity(nextLevel);
            gameActivity.finish();
        });

        return rootView;
    }
}
