package de.pawlidi.ipcamview.android.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mobsandgeeks.saripaar.Validator;

import de.pawlidi.ipcamview.android.ActivityComponent;
import de.pawlidi.ipcamview.android.DaggerActivityComponent;
import de.pawlidi.ipcamview.android.IPCamView;
import de.pawlidi.ipcamview.android.utils.Log;
import de.pawlidi.ipcamview.android.utils.PermissionUtil;

/**
 * This class represents the abstract base activity class.
 *
 * @author pawlidim
 */
public abstract class BaseActivity extends AppCompatActivity implements Validator.ValidationListener, ActivityCompat.OnRequestPermissionsResultCallback {

    /**
     * Id to identify a app permission request.
     */
    protected static final int PERMISSION_REQUEST_APP = 1;

    private static final String[] PERMISSIONS =  new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    protected Validator validator;

    private ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerActivityComponent.builder()
                .appComponent(getApp().getComponent()).build();
        validator = new Validator(this);
        validator.setValidationListener(this);

        initializeRate();
    }

    protected IPCamView getApp() {
        return (IPCamView) getApplicationContext();
    }

    protected ActivityComponent getComponent() {
        return component;
    }

    /**
     * Requests the required permission.
     *
     * @param view
     */
    protected void requestAppPermissions(View view) {
        ActivityCompat.requestPermissions(this, PERMISSIONS,
                PERMISSION_REQUEST_APP);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST_APP:
                Log.info("Received response for app permission request.");
                if (PermissionUtil.verifyPermissions(grantResults)) {
                    Log.info("App permission has now been granted.");
                } else {
                    Log.info("App permission was NOT granted.");
                }
                break;
            default:
                Log.error("Illegal switch case patŕameter!!!!");
            break;
        }
    }

    private void initializeRate() {
        /**
         AppRate.with(this)
         .setStoreType(StoreType.GOOGLEPLAY)
         //default is GOOGLEPLAY (Google Play), other options are
         //           AMAZON (Amazon Appstore) and
         //           SAMSUNG (Samsung Galaxy Apps)
         // App is launched more than 10 days later than installation.
         .setInstallDays((byte) 10) // default 10, 0 means install day
         // App is launched more than 10 times.
         .setLaunchTimes((byte) 10) // default 10
         // App is launched more than 1 days after neutral button clicked.
         .setRemindInterval((byte) 5) // default 1
         // App is launched X times and X % 1 = 0.
         .setRemindLaunchTimes((byte) 2) // default 1 (each launch)
         // App shows neutral dialog (Remind me later) by default.
         .setShowLaterButton(true) // default true
         .setDebug(false) // default false
         // To specify the callback when the (Remind me later) button is pressed
         .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
         @Override
         public void onClickButton(byte which) {
         Log.debug("Remind me later button klicked for rate {0}", Byte.toString(which));
         }
         })
         .monitor();

         if (AppRate.with(this).getStoreType() == StoreType.GOOGLEPLAY) {
         //Check that Google Play is available
         if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this) != ConnectionResult.SERVICE_MISSING) {
         // Show a dialog if meets conditions
         AppRate.showRateDialogIfMeetsConditions(this);
         }
         } else {
         // Show a dialog if meets conditions
         AppRate.showRateDialogIfMeetsConditions(this);
         }
         */
    }

    protected void showRateDialog() {
        //AppRate.with(this).showRateDialog(this);
    }
}
