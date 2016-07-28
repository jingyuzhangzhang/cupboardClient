package com.nwpu.wsner.ui.activities;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.nwpu.wsner.R;

/**
 * Created by lmy on 16/7/22.
 */
public class MyCursorAdapter extends CursorAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    // TextView add_con;
    public MyCursorAdapter(Context context, Cursor c) {
     super(context, c);
        mContext=context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return mInflater.inflate(R.layout.mylist_item,parent,false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex("product_name"));
        TextView p_name = (TextView)view.findViewById(R.id.product_name);
        p_name.setText("商品名称"+name);

    }
}
