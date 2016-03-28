package com.thenairn.rsscripts.lightlib.task.type;

/**
 * Created by thoma on 26/03/2016.
 */
public interface BackgroundTask extends ForegroundTask {
    boolean stopForeground();
}
