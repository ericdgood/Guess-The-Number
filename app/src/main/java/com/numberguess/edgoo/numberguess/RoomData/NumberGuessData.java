package com.numberguess.edgoo.numberguess.RoomData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "number_guess")
public class NumberGuessData {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "highLevel")
    private int hignLevel;

    @ColumnInfo(name = "higRange")
    private int hignRange;

    @ColumnInfo(name = "user")
    private String User;

    public NumberGuessData(int hignLevel, int hignRange, String User) {
        this.hignLevel = hignLevel;
        this.hignRange = hignRange;
        this.User = User;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHignLevel() {
        return hignLevel;
    }

    public int getHignRange() {
        return hignRange;
    }

    public String getUser() {
        return User;
    }

}
