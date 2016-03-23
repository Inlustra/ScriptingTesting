package com.thenairn.rsscripts.lightlib.task.selector;

import com.thenairn.rsscripts.lightlib.LightScript;
import com.thenairn.rsscripts.lightlib.task.LightTaskAdapter;
import com.thenairn.rsscripts.lightlib.utils.gui.LightGUI;

/**
 * Created by Thomas Nairn on 18/03/2016.
 */
public class TaskSelector extends LightTaskAdapter {

    private LightScript script;
    private TaskSelectorGUI gui;

    @Override
    public void onStart(LightScript script) {
        this.gui = new TaskSelectorGUI(script, script.getBot().getCanvas().getWidth(),
                script.getBot().getCanvas().getHeight());
        super.onStart(script);
        script.getBot().setHumanInputEnabled(true);
    }

    @Override
    public void onStop(LightScript script) {
        super.onStop(script);
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
