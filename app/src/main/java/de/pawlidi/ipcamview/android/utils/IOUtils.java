package de.pawlidi.ipcamview.android.utils;

import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 *
 */
public final class IOUtils {

    public static final String FILE_DIRECTORY = "CosmicTales";

    private IOUtils() {
        super();
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    /**
     * Create a file for saving
     * @return
     */
    public static File createImageFile() {
        // Create a media file name

        return new File(getDefaultAlbumDirectory().getPath() + File.separator +
                "IMG_"+ IOUtils.getTimeStamp() + ".jpg");
    }

    public static File getDefaultAlbumDirectory() {
        return getAlbumDirectory(FILE_DIRECTORY);
    }

    public static File getAlbumDirectory(final String albumName) {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = getStorageDirectory(albumName);

            if (storageDir != null) {
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
                        Log.error("Failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            Log.info("External storage is not mounted READ/WRITE.");
        }
        return storageDir;
    }

    private static File getStorageDirectory(final String albumName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            return new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES
                    ),
                    albumName
            );
        } else {
            return new File (
                    Environment.getExternalStorageDirectory()
                            + "/dcim/"
                            + albumName
            );
        }
    }
}
