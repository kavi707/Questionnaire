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

    public static final String GAME_TABLE_NAME = "games";
    public static final String GAME_ID = "game_id";
    public static final String CONTESTANT_NAME = "contestant_name";
    public static final String GRADE = "grade";
    public static final String GAME_STATUS = "game_status";
    public static final String CURRENT_QUE_NO = "current_que_no";
    public static final String TIME = "time";

    public QuestionnaireSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.dbContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createQuestionsTable(db);
        createGamesTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createQuestionsTable(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "create table " + QUESTIONS_TABLE_NAME + " (" +
                QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT not null, " +
                QUESTION + " text, " +
                GRADE + " int, " +
                QUESTION_LEVEL + " int, " +
                ANS_ONE + " text, " +
                ANS_TWO + " text, " +
                ANS_THREE + " text, " +
                ANS_FOUR + " text, " +
                ANS_CORRECT + " int " +
                ");";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    private void createGamesTable(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "create table " + GAME_TABLE_NAME + " (" +
                GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT not null, " +
                CONTESTANT_NAME + " text, " +
                GRADE + " int, " +
                GAME_STATUS + " int, " +
                CURRENT_QUE_NO + " int, " +
                TIME + " int " +
                ");";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    public void saveNewQuestion(Question question) {

        questionnaireDb = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(QUESTION, question.getQuestion());
        values.put(GRADE, question.getGrade());
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
                    getQuestion.setGrade(questionCursor.getInt(2));
                    getQuestion.setQuestionLevel(questionCursor.getInt(3));
                    getQuestion.setAnsOne(questionCursor.getString(4));
                    getQuestion.setAnsTwo(questionCursor.getString(5));
                    getQuestion.setAnsThree(questionCursor.getString(6));
                    getQuestion.setAnsFour(questionCursor.getString(7));
                    getQuestion.setCorrectAns(questionCursor.getInt(8));

                    questions.add(getQuestion);

                } while (questionCursor.moveToNext());
            }
            questionCursor.close();
        } catch (SQLiteException ex) {
            throw ex;
        }

        return questions;
    }

    public void saveNewGame(Games games) {

        questionnaireDb = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CONTESTANT_NAME, games.getContestantName());
        values.put(GRADE, games.getGrade());
        values.put(GAME_STATUS, games.getStatus());
        values.put(CURRENT_QUE_NO, games.getCurrentQueNo());
        values.put(TIME, games.getTime());

        try {
            questionnaireDb.insert(GAME_TABLE_NAME, null, values);
        } catch (SQLiteException ex) {
            throw ex;
        }
    }

    public List<Games> getAllGames() {

        List<Games> games = new ArrayList<Games>();
        questionnaireDb = this.getWritableDatabase();
        Games getGame = null;

        try {
            String queryString = "SELECT * FROM " + GAME_TABLE_NAME;
            Cursor gamesCursor = questionnaireDb.rawQuery(queryString, null);

            gamesCursor.moveToFirst();

            if (!gamesCursor.isAfterLast()) {
                do {
                    getGame = new Games();
                    getGame.setGameId(gamesCursor.getInt(0));
                    getGame.setContestantName(gamesCursor.getString(1));
                    getGame.setGrade(gamesCursor.getInt(2));
                    getGame.setStatus(gamesCursor.getInt(3));
                    getGame.setCurrentQueNo(gamesCursor.getInt(4));
                    getGame.setTime(gamesCursor.getFloat(5));

                    games.add(getGame);

                } while (gamesCursor.moveToNext());
            }
            gamesCursor.close();
        } catch (SQLiteException ex) {
            throw ex;
        }

        return games;
    }
}
