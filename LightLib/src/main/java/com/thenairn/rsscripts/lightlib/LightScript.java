package com.thenairn.rsscripts.lightlib;


import com.google.common.base.Throwables;
import com.thenairn.rsscripts.lightlib.task.LightTask;
import com.thenairn.rsscripts.lightlib.task.selector.TaskDescriptor;
import com.thenairn.rsscripts.lightlib.task.selector.ScriptSelector;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.script.Script;

import java.awt.*;

@Slf4j
public abstract class LightScript extends Script {

    private ScriptSelector selector;
    private LightTask innerScript;

    private volatile boolean isReady = false;

    @Override
    @SuppressWarnings("deprecation")
    public void onStart() throws InterruptedException {
        super.onStart();
        log.debug("Starting Inlustra.");
        try {
            LightAPI.get().exchangeContext(this.getBot());
            this.selector = new ScriptSelector();
            this.selector.onStart(this);
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
        log.trace("Painting Inlustra.");
        try {
            if (innerScript != null) {
                innerScript.gui().onPaint(g2d);
            } else {
                selector.gui().onPaint(g2d);
            }
        } catch (Exception e) {
            log(Throwables.getStackTraceAsString(e));
        }
    }

    @Override
    public int onLoop() throws InterruptedException {
        try {
            if (innerScript == null)
                return selector.onLoop();
            return innerScript.onLoop();
        } catch (Exception e) {
            log(Throwables.getStackTraceAsString(e));
            return -1;
        }
    }

    public void setInnerScript(LightTask innerScript) {
        if (this.innerScript != null)
            this.innerScript.onStop(this);
        this.innerScript = innerScript;
        this.innerScript.onStart(this);
    }

    public abstract TaskDescriptor[] getDescriptors();
}
