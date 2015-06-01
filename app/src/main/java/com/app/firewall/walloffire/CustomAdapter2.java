package com.app.firewall.walloffire;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Tyrell on 5/15/2015.
 */
public class CustomAdapter2 extends BaseAdapter {

    Context context;
    List<RowItem> rowItem;

    CustomAdapter2(Context context, List<RowItem> rowItem){}

    @Override
    public int getCount() {
        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
