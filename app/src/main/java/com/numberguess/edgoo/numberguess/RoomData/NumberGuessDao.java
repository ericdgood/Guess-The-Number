package com.numberguess.edgoo.numberguess.RoomData;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NumberGuessDao {

    @Insert
    void insertAll(NumberGuessData... highScore);

    @Query("SELECT max(highLevel) FROM number_guess")
    int gethighLevel();

    @Query("SELECT max(higRange) FROM number_guess")
    int gethighRange();
}
