package de.pawlidi.ipcamview.android.event;

import java.io.Serializable;

/**
 *
 *
 *
 */
public abstract class BaseEvent implements Serializable {

    protected final String name;
    protected final String from;

    public BaseEvent(String name, String from) {
        this.name = name;
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return name;
    }
}
