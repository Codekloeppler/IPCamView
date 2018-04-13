package de.pawlidi.ipcamview.android;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.raizlabs.android.dbflow.config.FlowManager;

import de.pawlidi.ipcamview.android.utils.Log;

/**
 * Main application class for intialization.
 *
 * @pawlidim
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

    /**
     * Return current application component.
     *
     * @return component, to get
     */
    public AppComponent getComponent() {
        return component;
    }

    /**
     * For internal test use only
     * @param component
     */
    @VisibleForTesting
    public void setComponent(AppComponent component) {
        this.component = component;
    }
}
