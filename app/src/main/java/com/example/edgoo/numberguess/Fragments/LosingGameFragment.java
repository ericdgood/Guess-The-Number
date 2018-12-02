package com.example.edgoo.numberguess.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.edgoo.numberguess.GameActivity;
import com.example.edgoo.numberguess.Main_Activity;
import com.example.edgoo.numberguess.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LosingGameFragment extends Fragment {

    @BindView(R.id.losing_game_btn)
    Button losingGameBtn;
    @BindView(R.id.losing_game_answer)
    TextView losingGameAnswer;

    int randomNumber;
    GameActivity gameActivity;

    public LosingGameFragment() {
    }

    public void getLosingFragInfo(GameActivity gameActivity, int randomNumber) {
        this.gameActivity = gameActivity;
        this.randomNumber = randomNumber;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Create view
        View rootView = inflater.inflate(R.layout.losing_game_fragment, container, false);
        ButterKnife.bind(this, rootView);

        String losingString = "The random number was " + String.valueOf(randomNumber);
            losingGameAnswer.setText(losingString);

            losingGameBtn.setOnClickListener( v -> {
                Intent returnHome = new Intent(getActivity(), Main_Activity.class);
                startActivity(returnHome);
                gameActivity.finish();
            });

        return rootView;
    }
}
