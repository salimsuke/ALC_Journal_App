package com.journal.salimfgaier.journalalc.Repositories;

import android.arch.lifecycle.LiveData;

import com.journal.salimfgaier.journalalc.Database.LocalDB;
import com.journal.salimfgaier.journalalc.Database.Models.Journal;
import com.journal.salimfgaier.journalalc.Utility.AppExecutors;

import java.util.List;

public class JournalRepo {

    private static final Object LOCK = new Object();
    private static JournalRepo sInstance;
    private final LocalDB mDb;


    private JournalRepo(final LocalDB database) {
        this.mDb = database;
    }

    public static JournalRepo getInstance(final LocalDB database) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new JournalRepo(database);
            }
        }
        return sInstance;
    }

    public LiveData<Journal> getJournal(int journalId) {

        return mDb.journalDao().loadJournalById(journalId);
    }

    private void refreshJournals() {
        AppExecutors.getInstance().diskIO.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public LiveData<List<Journal>> getJournals() {
        refreshJournals();
        return mDb.journalDao().getAllJournals();

    }
}
