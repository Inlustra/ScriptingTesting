package com.thenairn.rsscripts.utils.script;


import com.google.common.base.Throwables;
import com.thenairn.rsscripts.utils.script.selector.InnerScriptDescriptor;
import com.thenairn.rsscripts.utils.script.selector.ScriptSelector;
import org.osbot.rs07.script.Script;

import java.awt.*;

/**
 * Created by thoma on 15/03/2016.
 */
public abstract class LightScript extends Script {

    private LightAPI api;
    private ScriptSelector selector;
    private LightInnerScript innerScript;

    @Override
    @SuppressWarnings("deprecation")
    public void onStart() throws InterruptedException {
        super.onStart();
        log("Starting Inlustra.");
        try {
            this.api = new LightAPI();
            this.api.exchangeContext(this.getBot());
            this.selector = new ScriptSelector();
            this.selector.onStart(this);
        } catch (Exception e) {
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

    protected LightAPI getApi() {
        return api;
    }

    public void setInnerScript(LightInnerScript innerScript) {
        if (this.innerScript != null)
            this.innerScript.onStop(this);
        this.innerScript = innerScript;
        this.innerScript.onStart(this);
    }

    public abstract InnerScriptDescriptor[] getDescriptors();
}
