package com.android.kavi.questionnaire.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavi707 on 2/4/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class QuestionnaireSQLiteOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase questionnaireDb;
    private Context dbContext;

    public static final String DB_NAME = "local_questions.sqlite";
    public static final int VERSION = 1;

    public static final String QUESTIONS_TABLE_NAME = "questions";
    public static final String QUESTION_ID = "question_id";
    public static final String QUESTION = "question";
    public static final String QUESTION_LEVEL = "question_level";
    public static final String ANS_ONE = "ans_one";
    public static final String ANS_TWO = "ans_two";
    public static final String ANS_THREE = "ans_three";
    public static final String ANS_FOUR = "ans_four";
    public static final String ANS_CORRECT = "ans_correct";

    public QuestionnaireSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.dbContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createQuestionsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createQuestionsTable(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "create table " + QUESTIONS_TABLE_NAME + " (" +
                QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT not null, " +
                QUESTION + " text, " +
                QUESTION_LEVEL + " int, " +
                ANS_ONE + " text, " +
                ANS_TWO + " text, " +
                ANS_THREE + " text, " +
                ANS_FOUR + " text, " +
                ANS_CORRECT + " int " +
                ");";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    public void saveNewQuestion(Question question) {

        questionnaireDb = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(QUESTION, question.getQuestion());
        values.put(QUESTION_LEVEL, question.getQuestionLevel());
        values.put(ANS_ONE, question.getAnsOne());
        values.put(ANS_TWO, question.getAnsTwo());
        values.put(ANS_THREE, question.getAnsThree());
        values.put(ANS_FOUR, question.getAnsFour());
        values.put(ANS_CORRECT, question.getCorrectAns());

        try {
            questionnaireDb.insert(QUESTIONS_TABLE_NAME, null, values);
        } catch (SQLiteException ex) {
            throw ex;
        }
    }

    public List<Question> getAllLocations() {

        List<Question> questions = new ArrayList<Question>();
        questionnaireDb = this.getWritableDatabase();
        Question getQuestion = null;

        try {
            String queryString = "SELECT * FROM " + QUESTIONS_TABLE_NAME;
            Cursor questionCursor = questionnaireDb.rawQuery(queryString, null);

            questionCursor.moveToFirst();

            if (!questionCursor.isAfterLast()) {
                do {
                    getQuestion = new Question();
                    getQuestion.setQuestionId(questionCursor.getInt(0));
                    getQuestion.setQuestion(questionCursor.getString(1));
                    getQuestion.setQuestionLevel(questionCursor.getInt(2));
                    getQuestion.setAnsOne(questionCursor.getString(3));
                    getQuestion.setAnsTwo(questionCursor.getString(4));
                    getQuestion.setAnsThree(questionCursor.getString(5));
                    getQuestion.setAnsFour(questionCursor.getString(6));
                    getQuestion.setCorrectAns(questionCursor.getInt(7));

                    questions.add(getQuestion);

                } while (questionCursor.moveToNext());
            }
            questionCursor.close();
        } catch (SQLiteException ex) {
            throw ex;
        }

        return questions;
    }
}
