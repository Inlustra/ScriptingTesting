package com.thenairn.rsscripts.lightlib.utils.gui;

import com.thenairn.rsscripts.lightlib.utils.gui.event.LightMouseEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.canvas.paint.Painter;

import java.awt.*;

@Data
@Slf4j
public abstract class LightComponent implements Painter {

    private int x, y;
    private int width, height;
    private LightContainer parent = null;

    protected Paint background = Color.white;
    protected Paint foreground = Color.black;
    protected boolean hidden = false;
    protected boolean opaque = false;

    public LightComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isWithin(int x, int y) {
        if (x > this.getX() && x < getX() + getWidth()) {
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
        paintBackground(graphics2D);
        paintDebug(graphics2D);
        paintComponent(graphics2D);
    }


    protected void paintDebug(Graphics2D g2d) {
        g2d.setColor(Color.red);
        g2d.drawRect(0, 0, width - 1, height - 1);
    }

    protected void paintBackground(Graphics2D g2d) {
        if (!opaque) {
            return;
        }
        g2d.setPaint(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public LightGUI getRootComponent() {
        LightContainer container = getParent();
        while (!(container instanceof LightGUI)) {
            container = container.getParent();
        }
        return (LightGUI) container;
    }

    public abstract void paintComponent(Graphics2D g2d);

    public boolean mouseClicked(LightMouseEvent event) {
        log.debug(this.getClass().getSimpleName() + " Clicked");
        return false;
    }

    public boolean mousePressed(LightMouseEvent event) {
        log.debug(this.getClass().getSimpleName() + " Pressed");
        return false;
    }

    public boolean mouseReleased(LightMouseEvent event) {
        log.debug(this.getClass().getSimpleName() + " Released");
        return false;
    }

    public boolean mouseEntered(LightMouseEvent event) {
        log.debug(this.getClass().getSimpleName() + " Entered");
        return false;
    }

    public boolean mouseExited(LightMouseEvent event) {
        log.debug(this.getClass().getSimpleName() + " Exited");
        return false;
    }

    public boolean mouseMoved(LightMouseEvent event) {
        log.debug(this.getClass().getSimpleName() + " Moved");
        return false;
    }

    public boolean mouseDragged(LightMouseEvent event) {
        log.debug(this.getClass().getSimpleName() + " Dragged");
        return false;
    }
}
