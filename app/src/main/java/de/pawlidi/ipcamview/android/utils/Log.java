package de.pawlidi.ipcamview.android.utils;

import com.path.android.jobqueue.log.CustomLogger;

import java.text.MessageFormat;

/**
 *
 *
 *
 */
public final class Log {

    private Log() {
        super();
    }

    private static final String TAG = "[IPCAMVIEW]";

    public static void debug(String msg, Object... args) {
        android.util.Log.d(TAG, Log.interpolate(msg, args));
    }

    public static void info(String msg, Object... args) {
        android.util.Log.i(TAG, Log.interpolate(msg, args));
    }

    public static void error(Throwable t, String msg, Object... args) {
        android.util.Log.e(TAG, Log.interpolate(msg, args), t);
    }

    public static void error(String msg, Object... args) {
        android.util.Log.e(TAG, Log.interpolate(msg, args));
    }

    /**
     * Replace all EL expressions in the form {...} with their evaluated values and
     * current locale.
     *
     * @param string
     *            a template
     * @param locale
     *            a locale
     * @return the interpolated string
     */
    private static String interpolate(String messageText, Object... arguments) {
        MessageFormat messageFormat = new MessageFormat(messageText);
        return messageFormat.format(arguments);
    }

    public static CustomLogger getJobLogger() {
        return instance;
    }

    private static final CustomLogger instance = new CustomLogger() {
        @Override
        public boolean isDebugEnabled() {
            return true;
        }

        @Override
        public void d(String text, Object... args) {
            android.util.Log.d(TAG, String.format(text,args));
        }

        @Override
        public void e(Throwable t, String text, Object... args) {
            android.util.Log.e(TAG, String.format(text, args), t);
        }

        @Override
        public void e(String text, Object... args) {
            android.util.Log.e(TAG, String.format(text, args));
        }
    };
}
