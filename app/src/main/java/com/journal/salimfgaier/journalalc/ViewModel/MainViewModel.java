package com.journal.salimfgaier.journalalc.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.journal.salimfgaier.journalalc.ALCJournalApp;
import com.journal.salimfgaier.journalalc.Database.Models.Journal;
import com.journal.salimfgaier.journalalc.Repositories.JournalRepo;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private int journalId;
    private LiveData<Journal> journal;
    private JournalRepo journalRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.journalRepository = new ALCJournalApp().getJournalRepository();
    }


    public void init(int journalId) {
        if (this.journal != null) {
            return;
        }
        this.journal = this.journalRepository.getJournal(journalId);
    }

    public LiveData<Journal> getJournal() {
        return this.journal;
    }

    public LiveData<List<Journal>> getJournals() {
        return this.journalRepository.getJournals();
    }

}
