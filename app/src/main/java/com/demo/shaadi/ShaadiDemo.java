package com.demo.shaadi;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class ShaadiDemo extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(ShaadiDemo.this);
    }

}
