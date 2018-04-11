package de.pawlidi.ipcamview.android;

import dagger.Component;
import de.pawlidi.ipcamview.android.activity.MainActivity;

/**
 *
 *
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent {

    void inject(MainActivity mainActivity);
}
