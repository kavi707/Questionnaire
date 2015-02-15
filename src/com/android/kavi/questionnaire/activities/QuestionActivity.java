package com.android.kavi.questionnaire.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kavi.questionnaire.R;
import com.android.kavi.questionnaire.database.Question;
import com.android.kavi.questionnaire.database.QuestionnaireSQLiteOpenHelper;

import java.util.List;

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

    private int selectedGrade;
    private List<Question> questions;
    private int currentQueNo = 0;
    private Question currentQuestion;
    private QuestionnaireSQLiteOpenHelper questionnaireSQLiteOpenHelper = new QuestionnaireSQLiteOpenHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedGrade = extras.getInt("SELECTED_GRADE");
            questions = questionnaireSQLiteOpenHelper.getQuestionsFromGrade(selectedGrade);
        }

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

        logoImageView.performClick();

        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentQueNo <= 9 && questions.size() != 0) {
                    currentQuestion = questions.get(currentQueNo);

                    questionTextView.setText(currentQuestion.getQuestion());
                    answerOneButton.setText(currentQuestion.getAnsOne());
                    answerTwoButton.setText(currentQuestion.getAnsTwo());
                    answerThreeButton.setText(currentQuestion.getAnsThree());
                    answerFourButton.setText(currentQuestion.getAnsFour());

                    countDownTimer = new MyCountDownTimer(startTime, interval);
                    if (!timerHasStarted) {
                        countDownTimer.start();
                        timerHasStarted = true;
                    } else {
                        countDownTimer.cancel();
                        timerHasStarted = false;
                    }

                    currentQueNo++;
                }
            }
        });

        answerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonBgChange(1);
            }
        });

        answerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonBgChange(2);
            }
        });

        answerThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonBgChange(3);
            }
        });

        answerFourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonBgChange(4);
            }
        });
    }

    private void buttonBgChange(int ansNo) {

        answerOneButton.setBackgroundResource(R.drawable.question_bg);
        answerTwoButton.setBackgroundResource(R.drawable.question_bg);
        answerThreeButton.setBackgroundResource(R.drawable.question_bg);
        answerFourButton.setBackgroundResource(R.drawable.question_bg);

        if (ansNo == 1) {
            answerOneButton.setBackgroundResource(R.drawable.selected_question_bg);
        } else if (ansNo == 2) {
            answerTwoButton.setBackgroundResource(R.drawable.selected_question_bg);
        } else if (ansNo == 3) {
            answerThreeButton.setBackgroundResource(R.drawable.selected_question_bg);
        } else if (ansNo == 4) {
            answerFourButton.setBackgroundResource(R.drawable.selected_question_bg);
        }
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
