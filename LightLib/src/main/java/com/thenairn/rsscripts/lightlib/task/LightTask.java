package com.thenairn.rsscripts.lightlib.task;

import com.thenairn.rsscripts.lightlib.LightScript;
import com.thenairn.rsscripts.lightlib.utils.gui.LightGUI;

/**
 * Created by Thomas Nairn on 17/03/2016.
 */
public interface LightTask {

    void onStart(LightScript script);

    void onStop(LightScript script);

    int onLoop();

    LightGUI gui();
}
