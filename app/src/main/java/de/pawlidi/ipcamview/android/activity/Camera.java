package de.pawlidi.ipcamview.android.activity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import de.pawlidi.ipcamview.android.IPCamViewDatabase;

/**
 *
 * Base entity class for the camera configutation.
 *
 * @author pawlidim
 */
@Table(database = IPCamViewDatabase.class)
public class Camera  extends BaseModel implements Serializable{

    @PrimaryKey
    String id;

    @Column
    @NotNull
    String name;

    @Column
    String description;

    @Column
    String model;

    @Column
    String manufacturer;

    @Column
    String ip;
}
