package com.android.kavi.questionnaire.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.kavi.questionnaire.R;
import com.android.kavi.questionnaire.database.Games;
import com.android.kavi.questionnaire.views.GamesListItem;

import java.util.List;

/**
 * Created by kavi707 on 2/14/15.
 */
public class GamesItemAdapter extends BaseAdapter {

    private List<Games> gamesDataList;
    private Context context;

    public GamesItemAdapter(List<Games> gamesDataList, Context context) {
        this.gamesDataList = gamesDataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gamesDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return (gamesDataList == null)? null:gamesDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GamesListItem gamesListItem;

        if (convertView == null) {
            gamesListItem = (GamesListItem) View.inflate(context, R.layout.game_list_item, null);
        } else {
            gamesListItem = (GamesListItem) convertView;
        }

        gamesListItem.setGames(gamesDataList.get(position));
        return gamesListItem;
    }
}
