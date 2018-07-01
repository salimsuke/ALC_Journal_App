package com.journal.salimfgaier.journalalc.ViewModel.Factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.journal.salimfgaier.journalalc.Repositories.JournalRepo;
import com.journal.salimfgaier.journalalc.ViewModel.JournalViewModel;

public class JournalViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    //  private final Application mApplication;
    private final int mJournalId;
    private final JournalRepo mRepository;

    public JournalViewModelFactory(/*Application mApplication,*/JournalRepo journalRepository, int mJournalId) {
        //this.mApplication = mApplication;
        this.mJournalId = mJournalId;
        //this.mRepository = ((JournalApp) mApplication).getJournalRepository();
        this.mRepository = journalRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new JournalViewModel(/*mApplication,*/ mRepository, mJournalId);
    }
}