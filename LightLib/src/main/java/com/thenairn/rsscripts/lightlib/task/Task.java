package com.thenairn.rsscripts.lightlib.task;

import com.thenairn.rsscripts.lightlib.LightScript;

import java.awt.*;

/**
 * Created by thoma on 26/03/2016.
 */
public interface Task {
    void onStart(TaskedLightScript script);

    void onStop(TaskedLightScript script);

    int update();

}
