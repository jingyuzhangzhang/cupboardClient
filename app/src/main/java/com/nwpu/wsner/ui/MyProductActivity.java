package com.nwpu.wsner.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nwpu.wsner.R;
import com.nwpu.wsner.model.product_tb;

import org.litepal.crud.DataSupport;

import java.util.List;
/**
 * Created by lmy on 16/7/22.
 */
public class MyProductActivity extends Activity{
    private Cursor cursor =null;
    private  product_tb firstData=null;
   public void onCreate(Bundle saveInstanceState){
       super.onCreate(saveInstanceState);
       setContentView(R.layout.mylist);
       Intent intent=getIntent();
       String type=intent.getStringExtra("type");
       firstData=new product_tb("123","123","123","123","123","123");
       firstData.save();
       cursor = DataSupport.findBySQL("select product_name,id as _id from product_tb where product_type=?","123");

    final ListView listView = (ListView)findViewById(R.id.mylist);
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               int index=position;
               TextView textView=(TextView)view.findViewById(R.id.product_name);
               String del_data =textView.getText().toString();
               Toast.makeText(MyProductActivity.this, del_data, Toast.LENGTH_SHORT).show();
               return false;
           }
       });
       MyCursorAdapter myCursorAdapter = new MyCursorAdapter(this,cursor);
       listView.setAdapter(myCursorAdapter);

    }



}
