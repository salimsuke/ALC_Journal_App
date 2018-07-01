package com.journal.salimfgaier.journalalc.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.journal.salimfgaier.journalalc.Database.Models.Journal;
import com.journal.salimfgaier.journalalc.Repositories.JournalRepo;

public class JournalViewModel extends ViewModel {
    private LiveData<Journal> mJournal;

    public JournalViewModel(/*Application application,*/ JournalRepo journalRepository, final int journalId) {
//        super(application);
        this.mJournal = journalRepository.getJournal(journalId);
    }

    public LiveData<Journal> getJournal() {
        return mJournal;
    }


}
