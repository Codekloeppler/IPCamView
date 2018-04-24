package de.pawlidi.ipcamview.android;

import android.content.Context;

import com.path.android.jobqueue.JobManager;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;
import de.pawlidi.ipcamview.android.model.Model;

/**
 *
 *
 *
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface AppComponent {

    Model model();

    DatabaseDefinition database();

    Context appContext();

    EventBus eventBus();

    JobManager jobManager();

    void inject(Model model);
}
