package com.thenairn.rsscripts.lightlib.utils.gui;

import com.thenairn.rsscripts.lightlib.utils.gui.event.LightMouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.input.mouse.BotMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

@Slf4j
public class LightGUI extends LightContainer implements BotMouseListener, MouseMotionListener {


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
        if (this.mouseClicked(new LightMouseEvent(e, this))) {
            e.consume();
            log.trace("Consumed click event.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.mousePressed(new LightMouseEvent(e, this))) {
            e.consume();
            log.trace("Consumed Pressed event.");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.mouseReleased(new LightMouseEvent(e, this))) {
            e.consume();
            log.trace("Consumed Released event.");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.mouseEntered(new LightMouseEvent(e, this))) {
            e.consume();
            log.trace("Consumed Entered event.");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (this.mouseExited(new LightMouseEvent(e, this))) {
            e.consume();
            log.trace("Consumed Exited event.");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.mouseDragged(new LightMouseEvent(e, this))) {
            e.consume();
            log.trace("Consumed Dragged event.");
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.mouseMoved(new LightMouseEvent(e, this))) {
            e.consume();
            log.trace("Consumed Moved event.");
        }
    }

    @Override
    public boolean blockInput(Point point) {
        return super.blockInput(point);
    }
}
