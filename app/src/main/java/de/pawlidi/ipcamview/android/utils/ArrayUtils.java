package de.pawlidi.ipcamview.android.utils;

/**
 * Created by xim on 23.12.17.
 */

public final class ArrayUtils {

    public static boolean isEmpty(byte[] data) {
        return data != null && data.length > 0;
    }

    public static boolean isNotEmpty(byte[] data) {
        return !ArrayUtils.isEmpty(data);
    }
}
