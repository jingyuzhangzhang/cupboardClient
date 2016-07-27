package com.nwpu.wsner.ui.activities;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.nwpu.wsner.R;

import org.w3c.dom.Text;

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
        String date = cursor.getString(cursor.getColumnIndex("product_date"));
        String barcode = cursor.getString(cursor.getColumnIndex("product_barcode"));
        String function = cursor.getString(cursor.getColumnIndex("product_function"));
        TextView p_name = (TextView)view.findViewById(R.id.product_name);
        TextView p_bcode=(TextView)view.findViewById(R.id.product_barcode);
        if (function==null) {
            p_name.setText("商品名称:"+name+"  生产日期:"+date+"  功能:暂未收录");
        }
        else {
            p_name.setText("商品名称:" + name + "  生产日期:" + date + "  功能" + function);
        }
        p_bcode.setText(barcode);
    }

}
