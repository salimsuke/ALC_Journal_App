package com.journal.salimfgaier.journalalc;

import android.app.Application;

import com.journal.salimfgaier.journalalc.Database.LocalDB;
import com.journal.salimfgaier.journalalc.Repositories.JournalRepo;

public class ALCJournalApp extends Application {

    public LocalDB getDatabase() {
        return LocalDB.getInstance(this);
    }

    public JournalRepo getJournalRepository() {
        return JournalRepo.getInstance(getDatabase());
    }
}