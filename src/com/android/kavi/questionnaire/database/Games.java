package com.android.kavi.questionnaire.database;

/**
 * Created by kavi707 on 2/14/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class Games {

    private int gameId;
    private String contestantName;
    private int grade;
    private int status;
    private int currentQueNo;
    private float time;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getContestantName() {
        return contestantName;
    }

    public void setContestantName(String contestantName) {
        this.contestantName = contestantName;
    }

    public int getCurrentQueNo() {
        return currentQueNo;
    }

    public void setCurrentQueNo(int currentQueNo) {
        this.currentQueNo = currentQueNo;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
