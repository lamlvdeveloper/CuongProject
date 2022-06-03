package com.example.projectdoan;

import android.app.Application;
import android.content.Context;

/**
 * Created by anhch on 05/05/2017.
 */
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        this.context = this;
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }
}
