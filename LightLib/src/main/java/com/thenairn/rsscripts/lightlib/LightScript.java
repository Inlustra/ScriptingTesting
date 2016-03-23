package com.thenairn.rsscripts.lightlib;


import com.thenairn.rsscripts.lightlib.task.LightTask;
import com.thenairn.rsscripts.lightlib.task.selector.TaskDescriptor;
import com.thenairn.rsscripts.lightlib.task.selector.TaskSelector;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.script.Script;

import java.awt.*;

@Slf4j
public abstract class LightScript extends Script {

    private LightTask innerScript;

    private volatile boolean isReady = false;

    @Override
    @SuppressWarnings("deprecation")
    public void onStart() throws InterruptedException {
        super.onStart();
        try {
            LightAPI.get().exchangeContext(this.getBot());
            LightAPI.get().initializeModule();
            log.debug("Starting Inlustra.");
            setInnerScript(new TaskSelector());
            isReady = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            stop();
        }
    }

    @Override
    public void onExit() throws InterruptedException {
        super.onExit();
    }

    @Override
    public void onPaint(Graphics2D g2d) {
        super.onPaint(g2d);
        if (!isReady)
            return;
        try {
            innerScript.gui().onPaint(g2d);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            stop();
        }
    }

    @Override
    public int onLoop() throws InterruptedException {
        try {
            return innerScript.onLoop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            stop();
            return -1;
        }
    }

    public void setInnerScript(LightTask innerScript) {
        if (this.innerScript != null) {
            this.innerScript.onStop(this);
        }
        this.innerScript = innerScript;
        this.innerScript.onStart(this);
    }

    public abstract TaskDescriptor[] getDescriptors();
}
