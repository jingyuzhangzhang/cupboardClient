package com.nwpu.wsner.ui.navigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nwpu.wsner.data.model.NavigationDrawerItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nwpu.wsner.R;
import com.nwpu.wsner.ui.misc.BindableAdapter;

/**
 * Created by Michal Bialas on 19/07/14.
 */
public class NavigationDrawerAdapter extends BindableAdapter<NavigationDrawerItem> {

    private List<NavigationDrawerItem> items = Collections.emptyList();

    public NavigationDrawerAdapter(Context context) {
        super(context);
    }

    public void replaceWith(List<NavigationDrawerItem> items) {
        this.items = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    @Override
    public NavigationDrawerItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(R.layout.navigation_drawer_item, container, false);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public void bindView(NavigationDrawerItem item, int position, View view) {
        ((NavigationDrawerItemView) view).bindTo(item);

    }
}
