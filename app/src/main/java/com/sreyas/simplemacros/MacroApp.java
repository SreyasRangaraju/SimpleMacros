package com.sreyas.simplemacros;

import android.app.Application;
import android.content.Context;

public class MacroApp extends Application {
    private static MacroApp instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getAppContext() {
        return instance;
    }
}
