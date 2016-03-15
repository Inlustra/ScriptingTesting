package com.thenairn.rsscripts.gui.component;

import com.thenairn.rsscripts.gui.event.LightMouseEvent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.osbot.rs07.canvas.paint.Painter;

import java.awt.*;

@Data
public abstract class LightComponent implements Painter{

    protected int x, y;
    protected int width, height;

    protected boolean hidden;

    protected Color background;

    private boolean opaque = false;

    public LightComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isWithin(int x, int y) {
        if (x > this.getX() && x < getWidth() + getWidth()) {
            if (y > getY() && y < getY() + getHeight()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPaint(Graphics2D graphics2D) {
        if (hidden)
            return;
        paintComponent(graphics2D);
    }

    protected void paintBackground(Graphics2D g2d) {
        if (opaque) {
            return;
        }
        g2d.setColor(background);
        g2d.drawRect(0, 0, getWidth(), getHeight());

    }

    public void paintComponent(Graphics2D g2d) {
        this.paintBackground(g2d);
    }

    public boolean mouseClicked(LightMouseEvent event) {
        return false;
    }


    public boolean mousePressed(LightMouseEvent event) {
        return false;
    }

    public boolean mouseReleased(LightMouseEvent event) {
        return false;
    }

    public boolean mouseEntered(LightMouseEvent event) {
        return false;
    }

    public boolean mouseExited(LightMouseEvent event) {
        return false;
    }
}
