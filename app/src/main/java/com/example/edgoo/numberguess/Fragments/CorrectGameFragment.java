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

import com.example.edgoo.numberguess.GameActivity;
import com.example.edgoo.numberguess.Main_Activity;
import com.example.edgoo.numberguess.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CorrectGameFragment extends Fragment {

    @BindView(R.id.next_level_btn)
    Button nextLeveBtn;

    int level;
    int levelMaxRange;

    public CorrectGameFragment() {
    }

    public void getCorrectFragInfo(int level, int levelMaxRange){
        this.level = level;
        this.levelMaxRange = levelMaxRange;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Create view
        View rootView = inflater.inflate(R.layout.correct_game_fragment, container, false);
        ButterKnife.bind(this, rootView);

        nextLeveBtn.setOnClickListener( v -> {
            Intent nextLevel = new Intent(getActivity(), GameActivity.class);
            nextLevel.putExtra("level", level);
            nextLevel.putExtra("levelMax", levelMaxRange);
            startActivity(nextLevel);
        });

        return rootView;
    }
}
