package com.thenairn.rsscripts.gui.component;

import com.thenairn.rsscripts.gui.event.LightMouseEvent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by thoma on 15/03/2016.
 */
public class LightContainer extends LightComponent implements MouseListener {

    List<LightComponent> components = new LinkedList<>();

    public LightContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        super.onPaint(graphics2D);
        for (LightComponent component : components) {
            component.onPaint((Graphics2D) graphics2D.create(component.getX(),
                    component.getY(), component.getWidth(), component.getHeight()));
        }
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        super.paintComponent(g2d);
    }


    public LightComponent getElementAt(int x, int y) {
        ListIterator<LightComponent> li = components.listIterator(components.size());
        while (li.hasPrevious()) {
            LightComponent component = li.previous();
            if (component.isHidden()) {
                continue;
            }
            if (component.isWithin(x, y))
                return component;
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
            component.mouseClicked(new LightMouseEvent(e, component));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        component.mousePressed(new LightMouseEvent(e, component));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        component.mouseReleased(new LightMouseEvent(e, component));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        component.mouseEntered(new LightMouseEvent(e, component));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        component.mouseExited(new LightMouseEvent(e, component));
    }
}
