package com.nwpu.wsner.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.nwpu.wsner.R;
import com.nwpu.wsner.lib.CaptureActivity;

/**
 * Created by Michal Bialas on 19/07/14.
 */
public class FragmentManager extends Fragment {

    @InjectView(R.id.circleLayout)
    LinearLayout circleLayout;
    private Button btn;
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, containter, false);
        ButterKnife.inject(this, view);
        mContext=getActivity();
        ((GradientDrawable) circleLayout.getBackground())
                .setColor(getResources().getColor(R.color.material_pink));
        initView(view);
        return view;
    }
    public void initView(View view){
        btn=(Button)view.findViewById(R.id.scaner);
        btn.setOnClickListener(mListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
/**
 *    ADD onClickListener Here
 */
 private View.OnClickListener mListener= new View.OnClickListener(){

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.scaner:
            startActivity(new Intent(mContext, CaptureActivity.class));
                break;
        }
    }
};

}