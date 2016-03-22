package com.thenairn.rsscripts.lightlib.utils.gui;

import com.thenairn.rsscripts.lightlib.utils.gui.event.LightMouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by thoma on 15/03/2016.
 */
public class LightGUI extends LightContainer implements MouseListener, MouseMotionListener {


    public LightGUI(int width, int height) {
        super(0, 0, width, height);
        this.setOpaque(false);
    }

    public LightGUI(JComponent frame) {
        super(0, 0, frame.getHeight(), frame.getWidth());
        frame.addMouseMotionListener(this);
        frame.addMouseListener(this);
    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        //graphics2D.clearRect(0, 0, getWidth(), getHeight());
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
