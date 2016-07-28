package com.nwpu.wsner.ui.fragments;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.android.bluetoothlegatt.DeviceScanActivity;
import com.nwpu.wsner.R;
import com.nwpu.wsner.constants.SampleGattAttributes;
import com.nwpu.wsner.ui.services.BluetoothleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michal Bialas on 19/07/14.
 */
public class FragmentOne extends Fragment {

    @InjectView(R.id.circleLayout)
    LinearLayout circleLayout;

    Button GattButton;
    BluetoothleService mBluetoothleService;
    private BluetoothAdapter mBluetoothAdapter;
    private final static String TAG = BluetoothleService.class.getSimpleName();
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics =
            new ArrayList<ArrayList<BluetoothGattCharacteristic>>();





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, containter, false);




        GattButton = (Button) view.findViewById(R.id.GATTbutton);
        GattButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DeviceScanActivity.class);
                getActivity().startActivity(intent);


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
