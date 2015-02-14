package com.android.kavi.questionnaire.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.kavi.questionnaire.CommonUtils;
import com.android.kavi.questionnaire.R;
import com.android.kavi.questionnaire.database.Games;

/**
 * Created by kavi707 on 2/14/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class GamesListItem extends RelativeLayout {

    private TextView contestantNameTextView;
    private TextView gameStatusTextView;

    private Games games;

    public GamesListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        contestantNameTextView = (TextView) findViewById(R.id.contestantNameTextView);
        gameStatusTextView = (TextView) findViewById(R.id.gameStatusTextView);
    }

    public Games getGames() {
        return games;
    }

    public void setGames(Games games) {
        this.games = games;

        contestantNameTextView.setText(games.getContestantName());
        if (games.getStatus() == CommonUtils.COMPLETED)
            gameStatusTextView.setText("COMPLETED");
        else if (games.getStatus() == CommonUtils.ON_PROGRESS)
            gameStatusTextView.setText("ON_PROGRESS");
        else if (games.getStatus() == CommonUtils.STARTED)
            gameStatusTextView.setText("STARTED");
        else
            gameStatusTextView.setText("NON");
    }
}
