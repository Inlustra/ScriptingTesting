package com.thenairn.rsscripts.gui;

import com.thenairn.rsscripts.gui.event.LightMouseEvent;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by thoma on 15/03/2016.
 */
public class LightContainer extends LightComponent {

    List<LightComponent> components = new LinkedList<>();

    public LightContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void add(LightComponent... components) {
        for (LightComponent component : components) {
            component.setParent(this);
            this.components.add(component);
        }
    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        super.onPaint(graphics2D);
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        for (LightComponent component : components) {
            component.onPaint((Graphics2D) g2d.create(component.getX(),
                    component.getY(), component.getWidth(), component.getHeight()));
        }
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
    public boolean mouseClicked(LightMouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        if (component != null) {
            return component.mouseClicked(new LightMouseEvent(e, component));
        }
        return super.mouseClicked(e);
    }

    @Override
    public boolean mousePressed(LightMouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        if (component != null) {
            return component.mousePressed(new LightMouseEvent(e, component));
        }
        return super.mousePressed(e);
    }

    @Override
    public boolean mouseReleased(LightMouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        if (component != null) {
            return component.mouseReleased(new LightMouseEvent(e, component));
        }
        return super.mouseReleased(e);
    }

    @Override
    public boolean mouseEntered(LightMouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        if (component != null) {
            return component.mouseEntered(new LightMouseEvent(e, component));
        }
        return super.mouseEntered(e);
    }

    @Override
    public boolean mouseExited(LightMouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        if (component != null) {
            return component.mouseExited(new LightMouseEvent(e, component));
        }
        return super.mouseExited(e);
    }

    @Override
    public boolean mouseMoved(LightMouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        if (component != null) {
            return component.mouseMoved(new LightMouseEvent(e, component));
        }
        return super.mouseMoved(e);
    }

    @Override
    public boolean mouseDragged(LightMouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        if (component != null) {
            return component.mouseDragged(new LightMouseEvent(e, component));
        }
        return super.mouseDragged(e);
    }
}
