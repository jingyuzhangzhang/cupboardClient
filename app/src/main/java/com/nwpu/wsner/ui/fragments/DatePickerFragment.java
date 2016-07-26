package com.nwpu.wsner.ui.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.nwpu.wsner.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by weimingfei on 16/7/25.
 */


public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.nwpu.wsner.cupboardClient.date";
    private Date mDate=null;
    private Resultcallback mResultcallback;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

    }

    public interface Resultcallback{
        public void onResult(Date mDate);

    }

    private void sendResult(int resultCode){
        Intent i= new Intent();
        i.putExtra(EXTRA_DATE, mDate);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //获取date对象并且初始化
//        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);

        //Create a Calender to get the year,month, and day
        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);


        DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener(){
            public void onDateChanged(DatePicker view, int year, int month, int day){
                //Translate yeat,month, day into a Date object using a calendar
                mDate = new GregorianCalendar(year, month, day).getTime();

                //Update argument to preserve selected value on rotation
//                getArguments().putSerializable(EXTRA_DATE, mDate);
            }
        });


        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mResultcallback = (Resultcallback)getActivity();
                mResultcallback.onResult(mDate);
            }
        }).create();
    }
}
