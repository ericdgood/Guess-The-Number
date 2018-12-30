package com.numberguess.edgoo.numberguess.HighScore;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.numberguess.edgoo.numberguess.R;
import com.numberguess.edgoo.numberguess.RoomData.AppDatabase;
import com.numberguess.edgoo.numberguess.RoomData.NumberGuessData;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FirebaseHighScore extends Activity {

    @BindView(R.id.button_submit_your_score)
    Button btnSubmitYourScore;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mHigScoresDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_high_score);
        ButterKnife.bind(this);

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mHigScoresDatabaseReference = mFirebaseDatabase.getReference().child("high_scores");

        btnSubmitYourScore.setOnClickListener(v -> {
            final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "number_guess")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

            int highLevelData = db.numberGuessDao().gethighLevel();

            NumberGuessData highScore = new NumberGuessData(highLevelData, 0);

            mHigScoresDatabaseReference.push().setValue(highScore);

        });


    }

}
