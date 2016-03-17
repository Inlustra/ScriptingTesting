package com.thenairn.rsscripts.gui;

import com.thenairn.rsscripts.gui.event.LightMouseEvent;
import com.thenairn.rsscripts.script.LightAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by thoma on 15/03/2016.
 */
public class LightGUI extends LightContainer implements MouseListener, MouseMotionListener {

    private LightAPI api;

    public LightGUI(LightAPI api) {
        super(0, 0, api.getBot().getCanvas().getWidth(),
                api.getBot().getCanvas().getHeight());
        this.api = api;
        api.getBot().getCanvas().addMouseMotionListener(this);
        api.getBot().getCanvas().addMouseListener(this);
    }

    public LightGUI(JComponent frame) {
        super(0, 0, frame.getHeight(), frame.getWidth());
        frame.addMouseMotionListener(this);
        frame.addMouseListener(this);
    }

    public LightAPI getApi() {
        return api;
    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        graphics2D.clearRect(0, 0, getWidth(), getHeight());
        super.onPaint(graphics2D);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mouseClicked(new LightMouseEvent(e, this));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mousePressed(new LightMouseEvent(e, this));

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouseReleased(new LightMouseEvent(e, this));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.mouseEntered(new LightMouseEvent(e, this));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.mouseExited(new LightMouseEvent(e, this));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseDragged(new LightMouseEvent(e, this));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseMoved(new LightMouseEvent(e, this));
    }
}
