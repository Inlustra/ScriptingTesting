package com.thenairn.rsscripts.script;

import com.thenairn.rsscripts.utils.gui.LightGUI;

/**
 * Created by Thomas Nairn on 17/03/2016.
 */
public interface LightInnerScript {

    void onStart(LightScript script);

    void onStop(LightScript script);

    int onLoop();

    LightGUI gui();
}
