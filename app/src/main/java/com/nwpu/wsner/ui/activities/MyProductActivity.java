package com.nwpu.wsner.ui.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nwpu.wsner.R;
import com.nwpu.wsner.model.product_tb;

import org.litepal.crud.DataSupport;

/**
 * Created by lmy on 16/7/22.
 */
public class MyProductActivity extends ActionBarActivity {
    private Cursor cursor =null;
    private MyCursorAdapter myCursorAdapter;
   public void onCreate(Bundle saveInstanceState){
       super.onCreate(saveInstanceState);
       setContentView(R.layout.mylist);
       Intent intent=getIntent();
       String type=intent.getStringExtra("type");
       // 根据type查询不同的表，把表中的值存到cursor，传到Adapter中（this, Cursor c, true），把ListView setAdapter.
       // 需要显示的信息，名称，生产日期，功效，二维码
       cursor = DataSupport.findBySQL("select product_name,product_function,product_barcode,product_date,id as _id from product_tb where product_type=?",type);

     final ListView listView = (ListView)findViewById(R.id.mylist);
       myCursorAdapter = new MyCursorAdapter(this,cursor);
       listView.setAdapter(myCursorAdapter);

       //长按删除
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               int index = position;
               TextView textView = (TextView) view.findViewById(R.id.product_barcode);
               String del_data = textView.getText().toString();
               DataSupport.deleteAll(product_tb.class, "product_barcode=?", del_data);
               myCursorAdapter.notifyDataSetChanged();
               return false;
           }
       });

       getSupportActionBar().setIcon(R.drawable.ic_launcher);

    }



}
