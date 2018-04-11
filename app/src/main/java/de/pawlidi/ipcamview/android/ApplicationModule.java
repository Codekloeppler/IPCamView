package de.pawlidi.ipcamview.android;

import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;

import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.di.DependencyInjector;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.pawlidi.ipcamview.android.job.BaseJob;
import de.pawlidi.ipcamview.android.model.Model;
import de.pawlidi.ipcamview.android.utils.Log;

/**
 *
 *
 *
 */
@Module
public class ApplicationModule implements Serializable {

    private IPCamView ipCamView;

    public ApplicationModule(final IPCamView ipCamView) {
        super();
        this.ipCamView = ipCamView;
    }

    @Provides
    @Singleton
    public Model model(DatabaseDefinition database){
        return new Model(ipCamView, database);
    }

    @Provides
    @Singleton
    public DatabaseDefinition database() {
        return FlowManager.getDatabase(IPCamViewDatabase.class);
    }

    @Provides
    @Singleton
    public Context appContext() {
        return ipCamView;
    }

    @Provides
    @Singleton
    public EventBus eventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public JobManager jobManager() {
        Configuration config = new Configuration.Builder(ipCamView)
                .consumerKeepAlive(45)
                .maxConsumerCount(3)
                .minConsumerCount(1)
                .customLogger(Log.getJobLogger())
                .injector(new DependencyInjector() {
                    @Override
                    public void inject(com.path.android.jobqueue.BaseJob job) {
                        if (job instanceof BaseJob) {
                            ((BaseJob) job).inject(ipCamView.getComponent());
                        }
                    }
                })
                .build();
        return new JobManager(ipCamView, config);
    }

    @Provides
    @Singleton
    public NotificationManagerCompat notificationCompat() {
        return NotificationManagerCompat.from(ipCamView);
    }
}
