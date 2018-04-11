package de.pawlidi.ipcamview.android.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 *
 *
 *
 */
public final class ViewUtils {

    private ViewUtils() {
        super();
    }

    public static void createToast(Context context, final String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void createSnackbar(View view, final String message) {
        Snackbar.make(view, "Camera permission was granted. Starting preview.",
                Snackbar.LENGTH_SHORT)
                .show();
    }

    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera camera = null;
        try {
            camera = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return camera; // returns null if camera is unavailable
    }
}
