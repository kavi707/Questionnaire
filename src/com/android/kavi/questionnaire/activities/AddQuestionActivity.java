package com.android.kavi.questionnaire.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.kavi.questionnaire.R;
import com.android.kavi.questionnaire.database.Question;
import com.android.kavi.questionnaire.database.QuestionnaireSQLiteOpenHelper;

import java.io.IOException;

/**
 * Created by kavi707 on 2/15/15.
 */
public class AddQuestionActivity extends Activity {

    private EditText questionEditText;
    private Button saveBtn;
    private Button createBtn;

    private QuestionnaireSQLiteOpenHelper questionnaireSQLiteOpenHelper = new QuestionnaireSQLiteOpenHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        setUpViews();
    }

    private void setUpViews() {
        questionEditText = (EditText) findViewById(R.id.newQuestionEditText);
        saveBtn = (Button) findViewById(R.id.saveQuestionBtn);
        createBtn = (Button) findViewById(R.id.createDatabaseBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = questionEditText.getText().toString();
                String[] params = question.split("~");

                Question newQuestion = new Question();
                newQuestion.setQuestion(params[0]);
                newQuestion.setGrade(Integer.valueOf(params[1]));
                newQuestion.setQuestionLevel(Integer.valueOf(params[2]));
                newQuestion.setAnsOne(params[3]);
                newQuestion.setAnsTwo(params[4]);
                newQuestion.setAnsThree(params[5]);
                newQuestion.setAnsFour(params[6]);
                newQuestion.setCorrectAns(Integer.valueOf(params[7]));

                questionnaireSQLiteOpenHelper.saveNewQuestion(newQuestion);
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    questionnaireSQLiteOpenHelper.backupDatabase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
