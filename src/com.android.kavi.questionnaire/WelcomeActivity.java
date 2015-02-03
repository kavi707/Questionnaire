package com.android.kavi.questionnaire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.kavi.questionnaire.QuestionActivity;

/**
 * Created by kavi707 on 1/3/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class WelcomeActivity extends Activity {

    private Thread thread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        thread = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(3000);
                    }
                }
                catch(InterruptedException ex){
                }

                onContinue();
            }
        };

        thread.start();
    }

    private void onContinue() {
        Intent questionIntent = new Intent(WelcomeActivity.this, QuestionActivity.class);
        startActivity(questionIntent);
        finish();
    }
}