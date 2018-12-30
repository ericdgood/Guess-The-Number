package com.numberguess.edgoo.numberguess.HighScore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.numberguess.edgoo.numberguess.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.Viewholder> {

    public HighScoreAdapter() {
    }

    class Viewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.highscore_level)
        TextView highScoreLevel;
        @BindView(R.id.highscore_name)
        TextView highScoreName;

        Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HighScoreAdapter.Viewholder viewholder, int i) {

    }

    @NonNull
    @Override
    public HighScoreAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.highscore_layout, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
