package com.journal.salimfgaier.journalalc.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.journal.salimfgaier.journalalc.R;

public class BaseActivity extends AppCompatActivity {

    @VisibleForTesting
    public ProgressDialog mProgressDialog;


    public static String ARG_FIREBASE_ID = "firebaseId";

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    /*
     * Custom snackBar show error message captured from server or local text
     * */
    public synchronized static Snackbar showErrorSnackBar(View view, Context context, String error) {
        final Snackbar snackBar = Snackbar.make(view, error, Snackbar.LENGTH_INDEFINITE);
//        snackBar.setAction(context.getResources().getString(R.string.ok), new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                snackBar.dismiss();
//            }
//        });
        // Changing message text color
        snackBar.setActionTextColor(context.getResources().getColor(R.color.white));
        // Changing action button text color
        View sbView = snackBar.getView();
        sbView.setBackgroundColor(context.getResources().getColor(R.color.redAlert));
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        return snackBar;
    }

    /*
     * Custom snackBar show error message captured from server or local text
     * */
    public static void showSuccessSnackBar(View view, Context context, String error) {
        final Snackbar snackBar = Snackbar.make(view, error, Snackbar.LENGTH_LONG);
        snackBar.setAction(context.getResources().getString(R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBar.dismiss();
            }
        });
        // Changing message text color
        snackBar.setActionTextColor(context.getResources().getColor(R.color.white));
        // Changing action button text color
        View sbView = snackBar.getView();
        sbView.setBackgroundColor(context.getResources().getColor(R.color.chat_green));
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackBar.show();
    }


}
