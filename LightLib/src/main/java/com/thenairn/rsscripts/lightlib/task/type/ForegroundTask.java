package com.thenairn.rsscripts.lightlib.task.type;

import com.thenairn.rsscripts.lightlib.task.Task;

/**
 * Created by thoma on 26/03/2016.
 */
public interface ForegroundTask extends Task {
    boolean isRunning();
}
