package com.thenairn.rsscripts.script;


import com.google.common.base.Throwables;
import com.thenairn.rsscripts.script.selector.InnerScriptDescriptor;
import com.thenairn.rsscripts.script.selector.ScriptSelector;
import org.osbot.rs07.script.Script;

import java.awt.*;

/**
 * Created by thoma on 15/03/2016.
 */
public abstract class LightScript extends Script {

    private ScriptSelector selector;
    private LightInnerScript innerScript;

    private volatile boolean isReady = false;

    @Override
    @SuppressWarnings("deprecation")
    public void onStart() throws InterruptedException {
        super.onStart();
        log("Starting Inlustra.");
        try {
            LightAPI.get().exchangeContext(this.getBot());
            this.selector = new ScriptSelector();
            this.selector.onStart(this);
            isReady = true;
        } catch (Exception e) {
            stop();
            log(Throwables.getStackTraceAsString(e));
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
        log("Painting Inlustra.");
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

    public void setInnerScript(LightInnerScript innerScript) {
        if (this.innerScript != null)
            this.innerScript.onStop(this);
        this.innerScript = innerScript;
        this.innerScript.onStart(this);
    }

    public abstract InnerScriptDescriptor[] getDescriptors();
}
