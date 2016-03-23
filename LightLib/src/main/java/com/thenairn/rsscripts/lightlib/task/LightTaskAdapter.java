package com.thenairn.rsscripts.lightlib.task;

import com.thenairn.rsscripts.lightlib.LightScript;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Thomas Nairn on 22/03/2016.
 */
@Slf4j
public abstract class LightTaskAdapter implements LightTask {
    @Override
    public void onStart(LightScript script) {
        log.trace("Task Starting");
        script.getBot().getCanvas().addMouseListener(this.gui());
        script.getBot().getCanvas().addMouseMotionListener(this.gui());
    }

    @Override
    public void onStop(LightScript script) {
        log.trace("Task Stopping");
        script.getBot().getCanvas().addMouseListener(this.gui());
        script.getBot().getCanvas().addMouseMotionListener(this.gui());
    }
}
