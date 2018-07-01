package com.journal.salimfgaier.journalalc.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.journal.salimfgaier.journalalc.Database.LocalDB;
import com.journal.salimfgaier.journalalc.Database.Models.Journal;
import com.journal.salimfgaier.journalalc.R;
import com.journal.salimfgaier.journalalc.Repositories.JournalRepo;
import com.journal.salimfgaier.journalalc.Utility.AppExecutors;
import com.journal.salimfgaier.journalalc.ViewModel.Factories.JournalViewModelFactory;
import com.journal.salimfgaier.journalalc.ViewModel.JournalViewModel;

import java.util.Date;

public class JournalDaoActivity extends AppCompatActivity {
    public final static String EXTRA_ENTRY_ID = "entry_id";
    public final static int DEFAULT_ENTRY_ID = 0;
    private static final String TAG = JournalDaoActivity.class.getSimpleName();
    private int mJournalId = DEFAULT_ENTRY_ID;
    private FloatingActionButton mFabSave;
    private EditText mEditTextTitle, mEditTextContent;
    private LocalDB mDb;
    private JournalRepo mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_dao);

        mEditTextTitle = findViewById(R.id.edit_text_title);
        mEditTextContent = findViewById(R.id.edit_text_content);
        Log.d(TAG, mEditTextTitle.getText().toString());
        mFabSave = findViewById(R.id.fab_save_journal);
        mFabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveFabClicked();
            }
        });
        mDb = LocalDB.getInstance(this);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_ENTRY_ID)) {
            mJournalId = intent.getIntExtra(EXTRA_ENTRY_ID, DEFAULT_ENTRY_ID);
            mRepository = JournalRepo.getInstance(mDb);
            JournalViewModelFactory factory
                    = new JournalViewModelFactory(mRepository, mJournalId);
            final JournalViewModel viewModel = ViewModelProviders.of(this, factory).get(JournalViewModel.class);
            // MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
            //  viewModel.init(mJournalId);
            viewModel.getJournal().observe(this, new Observer<Journal>() {
                @Override
                public void onChanged(@Nullable Journal journal) {
                    populateUI(journal);
                }
            });
        }

    }

    public void populateUI(Journal journal) {
        if (journal == null) return;

        mEditTextTitle.setText(journal.getTitle());
        mEditTextContent.setText(journal.getContent());
    }

    public void onSaveFabClicked() {
        final String title = mEditTextTitle.getText().toString();
        final String content = mEditTextContent.getText().toString();
        Log.d(TAG, title + "\t" + content);
        final Journal journal = new Journal(title, content, new Date());
        AppExecutors.getInstance().diskIO.execute(new Runnable() {
            @Override
            public void run() {
                if (mJournalId == DEFAULT_ENTRY_ID) {
                    mDb.journalDao().save(journal);
                } else {
                    journal.setId(mJournalId);
                    mDb.journalDao().updateJournal(journal);
                }

            }
        });
        finish();
    }
}
