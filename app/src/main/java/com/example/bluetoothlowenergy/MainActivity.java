package com.example.bluetoothlowenergy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import com.example.bluetoothlowenergy.viewmodel.BluetoothViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DeviceScan deviceScan;
    BluetoothViewModel bluetoothViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothViewModel = new ViewModelProvider(this).get(BluetoothViewModel.class);
        deviceScan = new DeviceScan(bluetoothViewModel);

        bluetoothViewModel.BluetoothDeviceLiveData.observe(this, new Observer<ArrayList<BluetoothDevice>>() {
            @Override
            public void onChanged(ArrayList<BluetoothDevice> bluetoothDevice) {
                // update ui.

            }
        });


    }
}