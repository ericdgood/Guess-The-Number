package com.numberguess.edgoo.numberguess.RoomData;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {NumberGuessData.class}, version = 12)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NumberGuessDao numberGuessDao();
}

//VERSION 7 - ADDED USERNAME FOR FIREBASE
//VERSION 12 - REMOVED ID FROM DATABASE
