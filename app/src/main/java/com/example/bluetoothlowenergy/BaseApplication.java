package com.example.bluetoothlowenergy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static BaseApplication instance;
    private Context context;
    private Activity activity;

    public static BaseApplication getInstance() {
        if (instance == null) {
            instance = new BaseApplication();
        }
        return instance;
    }

    public Context getContext() {
        context = getContext();
        return context;
    }

    public Context getApplicationContext() {
        context = getApplicationContext();
        return context;
    }

    public Context getBaseContext() {
        context = getBaseContext();
        return context;
    }

    public Activity getActivity() {
        activity = getActivity();
        return activity;
    }
}
