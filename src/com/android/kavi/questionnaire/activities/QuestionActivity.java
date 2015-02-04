package com.android.kavi.questionnaire.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kavi.questionnaire.R;

/**
 * Created by kavi707 on 2/3/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class QuestionActivity extends Activity {

    private ImageView logoImageView;

    private TextView questionTextView;
    private TextView timerTextView;

    private Button answerOneButton;
    private Button answerTwoButton;
    private Button answerThreeButton;
    private Button answerFourButton;

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;

    private final long startTime = 2 * 60 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setUpView();
    }

    private void setUpView() {
        logoImageView = (ImageView) findViewById(R.id.logoImageView);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerTextView.setTextColor(getResources().getColor(R.color.green));

        answerOneButton = (Button) findViewById(R.id.answerOneButton);
        answerTwoButton = (Button) findViewById(R.id.answerTwoButton);
        answerThreeButton = (Button) findViewById(R.id.answerThreeButton);
        answerFourButton = (Button) findViewById(R.id.answerFourButton);

        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO set new question and answers

                countDownTimer = new MyCountDownTimer(startTime, interval);
                if (!timerHasStarted) {
                    countDownTimer.start();
                    timerHasStarted = true;
                } else {
                    countDownTimer.cancel();
                    timerHasStarted = false;
                }
            }
        });

        answerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        answerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        answerThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        answerFourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            timerTextView.setText("Time's up!");
        }

        @Override
        public void onTick(long millisUntilFinished) {

            int fullSeconds = (int) (millisUntilFinished / 1000);
            int minutes = fullSeconds / 60;
            int seconds = fullSeconds % 60;
            if (minutes == 0) {
                timerTextView.setText("" + seconds);
                if (seconds < 10) {
                    timerTextView.setTextColor(getResources().getColor(R.color.red));
                }
            } else {
                timerTextView.setText("0" + minutes + " : " + seconds);
            }
        }
    }
}
