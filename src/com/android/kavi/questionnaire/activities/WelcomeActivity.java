package com.android.kavi.questionnaire.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.kavi.questionnaire.R;
import com.android.kavi.questionnaire.database.QuestionnaireSQLiteOpenHelper;

import java.io.IOException;

/**
 * Created by kavi707 on 1/3/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class WelcomeActivity extends Activity {

    private Thread thread;
    private QuestionnaireSQLiteOpenHelper questionnaireSQLiteOpenHelper = new QuestionnaireSQLiteOpenHelper(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        thread = new Thread() {
            @Override
            public void run() {

                //create database from given database file in assets
                try {
                    if (!questionnaireSQLiteOpenHelper.checkDataBase()) {
                        questionnaireSQLiteOpenHelper.createDatabase();
                        questionnaireSQLiteOpenHelper.openDataBase();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    synchronized (this) {
                        wait(3000);
                    } 
                } catch (InterruptedException ex) {
                }

                onContinue();
            }
        };

        thread.start();
    }

    private void onContinue() {
        Intent questionIntent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(questionIntent);
        finish();
    }
}