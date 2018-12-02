package com.example.edgoo.numberguess.RoomData;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {NumberGuessData.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NumberGuessDao numberGuessDao();
}
