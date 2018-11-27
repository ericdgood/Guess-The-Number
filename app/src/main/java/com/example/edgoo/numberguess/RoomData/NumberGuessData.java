package com.example.edgoo.numberguess.RoomData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "number_guess")
public class NumberGuessData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "numberOfGuesses")
    private final Integer numberOfGuess;

    @ColumnInfo(name = "starStatus")
    private int StarStatus;

    public NumberGuessData(Integer NumberOfGuesses, int StarStatus) {
        this.numberOfGuess = NumberOfGuesses;
        this.StarStatus = StarStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumberOfGuesses() {
        return numberOfGuess;
    }


}
