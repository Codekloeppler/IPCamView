package de.pawlidi.ipcamview.android;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.raizlabs.android.dbflow.config.FlowManager;

import de.pawlidi.ipcamview.android.utils.Log;

/**
 *
 *
 *
 *
 */
public class IPCamView extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.info("Start ip camera view application and initialize.....");
        FlowManager.init(this);
        Log.info("DB manager initialized....");
        component = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        Log.info("Components initialized....");
    }

    public AppComponent getComponent() {
        return component;
    }

    @VisibleForTesting
    public void setComponent(AppComponent component) {
        this.component = component;
    }
}
