package com.thenairn.rsscripts.script;


import org.osbot.rs07.script.Script;

import java.awt.*;

/**
 * Created by thoma on 15/03/2016.
 */
public abstract class LightScript extends Script {

    private final LightAPI api;

    @SuppressWarnings("deprecation")
    public LightScript() {
        this.api = new LightAPI();
        api.exchangeContext(this.getBot());
    }

    @Override
    public void onPaint(Graphics2D g2d) {
        super.onPaint(g2d);
        api.onPaint(g2d);
    }

    protected LightAPI getApi() {
        return api;
    }
}
