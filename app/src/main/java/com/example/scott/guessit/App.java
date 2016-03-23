package com.example.scott.guessit;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

/**
 * Created by scott on 16-03-22.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Don't do this! This is just so cold launches take some time
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
    }
}
