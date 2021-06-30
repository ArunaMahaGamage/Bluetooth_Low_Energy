package com.example.bluetoothlowenergy.viewmodel;

import android.bluetooth.BluetoothDevice;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class BluetoothViewModel extends ViewModel {
    public final MutableLiveData<ArrayList<BluetoothDevice>> BluetoothDeviceLiveData = new MutableLiveData<>();
}
