package com.android.kavi.questionnaire.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.kavi.questionnaire.CommonUtils;
import com.android.kavi.questionnaire.R;
import com.android.kavi.questionnaire.adapter.GamesItemAdapter;
import com.android.kavi.questionnaire.database.Games;
import com.android.kavi.questionnaire.database.QuestionnaireSQLiteOpenHelper;

import java.util.List;

/**
 * Created by kavi707 on 2/8/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class MainActivity extends Activity {

    private ImageView logoImageView;
    private LinearLayout collapseLinearLayout;

    private EditText contestantNameEditText;
    private Spinner gradeSpinner;
    private Button saveGameButton;
    private ListView gamesListView;

    private Context context = this;

    private GamesItemAdapter gamesItemAdapter;

    QuestionnaireSQLiteOpenHelper questionnaireSQLiteOpenHelper = new QuestionnaireSQLiteOpenHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
    }

    private void setUpView() {
        logoImageView = (ImageView) findViewById(R.id.mainLogoImageView);
        collapseLinearLayout = (LinearLayout) findViewById(R.id.collapse_layout);
        collapseLinearLayout.setVisibility(View.GONE);

        contestantNameEditText = (EditText) findViewById(R.id.newContestantNameEditText);
        gradeSpinner = (Spinner) findViewById(R.id.gradeSpinner);
        saveGameButton = (Button) findViewById(R.id.saveNewGameButton);

        gamesListView = (ListView) findViewById(R.id.contestantsListView);
        loadGamesToListView();

        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseLinearLayout.setVisibility( collapseLinearLayout.isShown()
                                ? View.GONE: View.VISIBLE );

            }
        });

        saveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contestantName = contestantNameEditText.getText().toString();
                int grade = gradeSpinner.getSelectedItemPosition();

                Games newGame = new Games();
                newGame.setContestantName(contestantName);
                newGame.setGrade(grade + 6);
                newGame.setStatus(CommonUtils.STARTED);

                questionnaireSQLiteOpenHelper.saveNewGame(newGame);
                loadGamesToListView();
            }
        });
    }

    private void loadGamesToListView() {
        List<Games> gamesDataList = questionnaireSQLiteOpenHelper.getAllGames();
        if (gamesDataList.size() != 0) {
            gamesItemAdapter = new GamesItemAdapter(gamesDataList, context);
            gamesListView.setAdapter(gamesItemAdapter);
        }
    }
}
