package de.pawlidi.ipcamview.android;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 *
 *
 *
 */
@Database(name = IPCamViewDatabase.NAME, version = IPCamViewDatabase.VERSION)
public interface IPCamViewDatabase {
    final String NAME = "IPCamView";

    final int VERSION = 1;
}
