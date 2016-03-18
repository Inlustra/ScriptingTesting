package com.thenairn.rsscripts.utils.gui;

import com.thenairn.rsscripts.utils.gui.event.LightMouseEvent;
import lombok.Data;
import org.osbot.rs07.canvas.paint.Painter;

import java.awt.*;

@Data
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
        if (opaque) {
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
        System.out.println(this.getClass().getSimpleName() + " Clicked");
        System.out.println("At " + event.getX() + " " + event.getY());
        return false;
    }

    public boolean mousePressed(LightMouseEvent event) {
        System.out.println(this.getClass().getSimpleName() + " Pressed");
        System.out.println("At " + event.getX() + " " + event.getY());
        return false;
    }

    public boolean mouseReleased(LightMouseEvent event) {
        System.out.println(this.getClass().getSimpleName() + " Released");
        System.out.println("At " + event.getX() + " " + event.getY());
        return false;
    }

    public boolean mouseEntered(LightMouseEvent event) {
        System.out.println(this.getClass().getSimpleName() + " Entered");
        System.out.println("At " + event.getX() + " " + event.getY());
        return false;
    }

    public boolean mouseExited(LightMouseEvent event) {
        System.out.println(this.getClass().getSimpleName() + " Exited");
        System.out.println("At " + event.getX() + " " + event.getY());
        return false;
    }

    public boolean mouseMoved(LightMouseEvent event) {
        System.out.println(this.getClass().getSimpleName() + " Moved");
        System.out.println("At " + event.getX() + " " + event.getY());
        return false;
    }

    public boolean mouseDragged(LightMouseEvent event) {
        System.out.println(this.getClass().getSimpleName() + " Dragged");
        System.out.println("At " + event.getX() + " " + event.getY());
        return false;
    }
}