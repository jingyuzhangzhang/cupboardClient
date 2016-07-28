package com.nwpu.wsner.ui.fragments;

import android.bluetooth.BluetoothGattService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.nwpu.wsner.R;
import com.nwpu.wsner.constants.SampleGattAttributes;
import com.nwpu.wsner.ui.services.BluetoothleService;

import java.util.List;

/**
 * Created by Michal Bialas on 19/07/14.
 */
public class FragmentOne extends Fragment {

    @InjectView(R.id.circleLayout)
    LinearLayout circleLayout;

    Button GattButton;
    BluetoothleService mBluetoothleService;
    private final static String TAG = BluetoothleService.class.getSimpleName();


//    // Code to manage Service lifecycle.
//    private final ServiceConnection mServiceConnection = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName componentName, IBinder service) {
//            mBluetoothleService = ((BluetoothleService.LocalBinder) service).getService();
//            if (!mBluetoothleService.initialize()) {
//                Log.e(TAG, "Unable to initialize Bluetooth");
//                getActivity().finish();
//            }
//            mBluetoothleService.initialize();
//            // Automatically connects to the device upon successful start-up initialization.
//            mBluetoothleService.connect(SampleGattAttributes.DerviceAdreess);
//
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//            mBluetoothleService = null;
//        }
//    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, containter, false);
        GattButton = (Button) view.findViewById(R.id.GATTbutton);
        GattButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent gattServiceIntent = new Intent(getActivity(),BluetoothleService.class);
//                getActivity().bindService(gattServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //clear service
//        getActivity().unbindService(mServiceConnection);
//        mBluetoothleService = null;
        ButterKnife.reset(this);
    }


}
