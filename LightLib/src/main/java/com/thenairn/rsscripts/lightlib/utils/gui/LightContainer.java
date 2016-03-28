package com.thenairn.rsscripts.lightlib.utils.gui;

import com.thenairn.rsscripts.lightlib.utils.gui.event.LightMouseEvent;
import javafx.scene.effect.Light;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@Slf4j
public class LightContainer extends LightComponent {
    private final Object lock = new Object();

    private LightComponent mouseOver = null;
    List<LightComponent> components = new LinkedList<>();

    public LightContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void add(LightComponent... components) {
        for (LightComponent component : components) {
            component.setParent(this);
            synchronized (lock) {
                this.components.add(component);
            }
        }
    }

    public void remove(LightComponent... components) {
        for (LightComponent component : components) {
            synchronized (lock) {
                this.components.remove(component);
            }
        }
    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        super.onPaint(graphics2D);
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        synchronized (lock) {
            for (LightComponent component : components) {
                component.onPaint((Graphics2D) g2d.create(component.getX(),
                        component.getY(), component.getWidth(), component.getHeight()));
            }
        }
    }


    public LightComponent getElementAt(int x, int y) {
        synchronized (lock) {
            ListIterator<LightComponent> li = components.listIterator(components.size());
            while (li.hasPrevious()) {
                LightComponent component = li.previous();
                if (component.isHidden()) {
                    continue;
                }
                if (component.isWithin(x, y)) {
                    return component;
                }
            }
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
        checkMouseOver(e, component);
        if (component != null) {
            return component.mouseMoved(new LightMouseEvent(e, component));
        }
        return super.mouseMoved(e);
    }

    private void checkMouseOver(LightMouseEvent event, LightComponent component) { //TODO, This can be done better
        if (mouseOver == null && component != null) {
            component.mouseEntered(new LightMouseEvent(event, component));
            mouseOver = component;
            return;
        }
        if (mouseOver != null && component != null) {
            if (mouseOver == component) {
                return;
            }
            mouseOver.mouseExited(new LightMouseEvent(event, mouseOver));
            component.mouseEntered(new LightMouseEvent(event, component));
            mouseOver = component;
        }
        if (mouseOver != null && component == null) {
            mouseOver.mouseExited(new LightMouseEvent(event, mouseOver));
            mouseOver = null;
        }
    }

    @Override
    public boolean mouseDragged(LightMouseEvent e) {
        LightComponent component = getElementAt(e.getX(), e.getY());
        if (component != null) {
            return component.mouseDragged(new LightMouseEvent(e, component));
        }
        return super.mouseDragged(e);
    }

    @Override
    public boolean blockInput(Point e) {
        LightComponent component = getElementAt((int) e.getX(), (int) e.getY());
        if (component != null) {
            return component.blockInput(new Point((int) e.getX() - component.getX(), (int) e.getY() - component.getY()));
        }
        return false;
    }
}
