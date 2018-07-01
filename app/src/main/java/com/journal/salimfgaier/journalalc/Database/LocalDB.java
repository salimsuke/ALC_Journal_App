package com.journal.salimfgaier.journalalc.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.journal.salimfgaier.journalalc.DataAccessLayer.JournalDAO;
import com.journal.salimfgaier.journalalc.Database.Models.Journal;
import com.journal.salimfgaier.journalalc.Utility.DateConverter;


@Database(entities = {Journal.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class LocalDB extends RoomDatabase {
    private static final String LOG_TAG = LocalDB.class.getSimpleName();
    private static final String DATABASE_NAME = "local-journal-db";
    private static final Object LOCK = new Object();
    private static LocalDB sInstance;

    public static LocalDB getInstance(Context context) {
        synchronized (LOCK) {
            if (sInstance == null) {
                Log.d(LOG_TAG, "new database instance creating");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        LocalDB.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
            }
        }
        Log.d(LOG_TAG, "Getting database instance");
        return sInstance;
    }

    public abstract JournalDAO journalDao();
}
