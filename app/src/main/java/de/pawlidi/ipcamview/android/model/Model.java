package de.pawlidi.ipcamview.android.model;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import javax.inject.Inject;

import de.pawlidi.ipcamview.android.AppComponent;
import de.pawlidi.ipcamview.android.IPCamView;

/**
 *
 *
 *
 */
public class Model implements Serializable {

    @Inject
    transient EventBus eventBus;

    private final DatabaseDefinition database;
    private final AppComponent component;

    public Model(final IPCamView ipCamView, final DatabaseDefinition database) {
        this.component = ipCamView.getComponent();
        this.component.inject(this);
        this.database = database;
    }
}
