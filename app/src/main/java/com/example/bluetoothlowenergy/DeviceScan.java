package com.example.bluetoothlowenergy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.example.bluetoothlowenergy.viewmodel.BluetoothViewModel;

import java.util.ArrayList;

public class DeviceScan {
//    private LeDeviceListAdapter mLeDeviceListAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;

    private static final int REQUEST_ENABLE_BT = 1;
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    private ArrayList<BluetoothDevice> mLeDevices = new ArrayList<BluetoothDevice>();
    BluetoothViewModel bluetoothViewModel;

    BaseApplication instance = BaseApplication.getInstance();

    public DeviceScan(BluetoothViewModel bluetoothViewModel) {
        this.bluetoothViewModel = bluetoothViewModel;
    }

    public void checkBLESupport() {
        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        Context context = BaseApplication.getInstance().getContext();
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            instance.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
                }
            });
//            finish();
        }
    }

    public void initBluetoothAdapter() {
        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
        // BluetoothAdapter through BluetoothManager.
        Context context = BaseApplication.getInstance().getContext();
        final BluetoothManager bluetoothManager =
                (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // Checks if Bluetooth is supported on the device.
        if (mBluetoothAdapter == null) {
            instance.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, R.string.error_bluetooth_not_supported, Toast.LENGTH_SHORT).show();
                }
            });
//            finish();
            return;
        }
    }

    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
//                    invalidateOptionsMenu();
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
//        invalidateOptionsMenu();
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    instance.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(!mLeDevices.contains(device)) {
                                mLeDevices.add(device);
                                bluetoothViewModel.BluetoothDeviceLiveData.postValue(mLeDevices);
                            }
                        }
                    });
                }
            };

}
