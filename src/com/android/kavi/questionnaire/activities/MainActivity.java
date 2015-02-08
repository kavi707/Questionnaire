package com.android.kavi.questionnaire.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.kavi.questionnaire.R;

/**
 * Created by kavi707 on 2/8/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class MainActivity extends Activity {

    private ImageView logoImageView;
    private LinearLayout collapseLinearLayout;

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

        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseLinearLayout.setVisibility( collapseLinearLayout.isShown()
                                ? View.GONE: View.VISIBLE );

            }
        });
    }
}
