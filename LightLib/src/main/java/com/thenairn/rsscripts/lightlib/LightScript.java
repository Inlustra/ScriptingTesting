package com.thenairn.rsscripts.lightlib;


import com.thenairn.rsscripts.lightlib.utils.gui.LightGUI;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.canvas.WrappedCanvas;
import org.osbot.rs07.script.Script;

import java.awt.*;

@Slf4j
public abstract class LightScript extends Script {

    @Getter
    private LightGUI gui;

    @Override
    @SuppressWarnings("deprecation")
    public void onStart() throws InterruptedException {
        try {
            WrappedCanvas canvas = this.getBot().getCanvas();
            this.gui = new LightGUI(canvas.getWidth(), canvas.getHeight());
            setupGUI(this.gui);
            super.onStart();
            LightAPI.get().exchangeContext(this.getBot());
            LightAPI.get().initializeModule();
            getBot().addMouseListener(this.gui);
            getBot().getCanvas().addMouseMotionListener(this.gui);
            log.debug("Starting Inlustra.");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            stop();
        }
    }

    @Override
    public void onExit() throws InterruptedException {
        super.onExit();
        getBot().removeMouseListener(this.gui);
        getBot().getCanvas().removeMouseMotionListener(this.gui);
    }

    @Override
    public void onPaint(Graphics2D g2d) {
        super.onPaint(g2d);
        try {
            if (gui == null) return;
            gui.onPaint(g2d);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            stop();
        }
    }

    @Override
    public int onLoop() throws InterruptedException {
        try {
            return safeOnLoop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            stop();
            return -1;
        }
    }

    protected abstract void setupGUI(LightGUI gui);

    protected abstract int safeOnLoop();

}
