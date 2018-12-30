package com.numberguess.edgoo.numberguess.HighScore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.numberguess.edgoo.numberguess.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.firebase.ui.auth.AuthUI.TAG;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.Viewholder> {

    private List<HighScore> highScores;
    private Context context;

    public HighScoreAdapter(Context context, List<HighScore> highScores) {
        this.context = context;
        this.highScores = highScores;
    }

    class Viewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.highscore_level)
        TextView highScoreLevel;
        @BindView(R.id.highscore_name)
        TextView highScoreName;

        Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HighScoreAdapter.Viewholder viewholder, int position) {
        String highLevelConCat = "Level " + String.valueOf(highScores.get(position).getHighLevel());
        viewholder.highScoreLevel.setText(highLevelConCat);
        viewholder.highScoreName.setText(highScores.get(position).getUsername());
    }

    @NonNull
    @Override
    public HighScoreAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.highscore_layout, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public int getItemCount() {
        return highScores.size();
    }
}
