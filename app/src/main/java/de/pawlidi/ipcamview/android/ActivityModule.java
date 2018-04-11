package de.pawlidi.ipcamview.android;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 *
 *
 *
 */
@Module
public class ActivityModule {

    final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Context activityContext() {
        return activity;
    }
}
