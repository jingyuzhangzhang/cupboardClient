package com.nwpu.wsner.ui.fragments;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nwpu.wsner.R;
import com.nwpu.wsner.constants.Url;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Michal Bialas on 19/07/14.
 */
public class FragmentThree extends Fragment {

    @InjectView(R.id.circleLayout)
    LinearLayout circleLayout;

    TextView weatherTextView;
    TextView tempLowTextView;
    TextView tempHighTextView;
    TextView zwx_sTextView,ssd_sTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, containter, false);
//        ButterKnife.inject(this, view);
//        ((GradientDrawable) circleLayout.getBackground())
//                .setColor(getResources().getColor(R.color.material_purple));

        tempHighTextView = (TextView) view.findViewById(R.id.temp_highTextView);
        tempLowTextView = (TextView) view.findViewById(R.id.temp_lowTextView);
        weatherTextView = (TextView) view.findViewById(R.id.weatherTextView);
        ssd_sTextView = (TextView) view.findViewById(R.id.ssd_s);
        zwx_sTextView= (TextView) view.findViewById(R.id.zwx_s);

        AsyncHttpClient asyncHttpClient= new AsyncHttpClient();
        asyncHttpClient.post(Url.WETHER_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s= new String(bytes);
                Log.e("TEST","s:"+s);
//                String[] weathers=s.split("<status1>")[1].split("</status1>");
//                Log.e("test",weathers[0]);
//                weatherTextView.setText(weathers[0]);
                String[] list =s.split("<.*?>\n<.*?>");
                for (int j=0;j<list.length;j++){
                    Log.e("test",j+"****"+list[j]);
                }
                weatherTextView.setText(list[3]);
                tempHighTextView.setText(list[11]);
                tempLowTextView.setText(list[12]);
                ssd_sTextView.setText(list[39]);
                zwx_sTextView.setText(list[38]);



            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });







        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
