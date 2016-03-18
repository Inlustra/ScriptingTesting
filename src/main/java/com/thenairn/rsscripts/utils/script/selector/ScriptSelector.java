package com.thenairn.rsscripts.utils.script.selector;

import com.thenairn.rsscripts.utils.gui.LightGUI;
import com.thenairn.rsscripts.utils.script.LightInnerScript;
import com.thenairn.rsscripts.utils.script.LightScript;

import static org.pushingpixels.lafwidget.utils.LookUtils.log;

/**
 * Created by Thomas Nairn on 18/03/2016.
 */
public class ScriptSelector implements LightInnerScript {

    private LightScript script;
    private ScriptSelectorGUI gui;

    @Override
    public void onStart(LightScript script) {
        this.script = script;
        log("Starting Script Selector");
        this.gui = new ScriptSelectorGUI(script, script.getBot().getCanvas().getWidth(),
                script.getBot().getCanvas().getHeight());
    }

    @Override
    public void onStop(LightScript script) {

    }

    @Override
    public int onLoop() {
        return 20;
    }

    @Override
    public LightGUI gui() {
        return gui;
    }

}
