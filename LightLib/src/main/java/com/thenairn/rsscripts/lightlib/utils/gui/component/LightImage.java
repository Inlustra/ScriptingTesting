package com.thenairn.rsscripts.lightlib.utils.gui.component;

import com.thenairn.rsscripts.lightlib.utils.gui.LightComponent;
import com.thenairn.rsscripts.lightlib.utils.gui.event.LightMouseEvent;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Thomas Nairn on 16/03/2016.
 */
public class LightImage extends LightComponent {

    BufferedImage image;

    public LightImage(int x, int y, int width, int height, BufferedImage image) {
        super(x, y, width, height);
        this.setOpaque(true);
        this.image = image;
    }


    public LightImage(int x, int y, BufferedImage image) {
        this(x, y, image.getWidth(), image.getHeight(), image);
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);

    }

    @Override
    public boolean mouseClicked(LightMouseEvent event) {
        return true;
    }

    @Override
    public boolean mousePressed(LightMouseEvent event) {
        return true;
    }

    @Override
    public boolean mouseReleased(LightMouseEvent event) {
        return true;
    }

    @Override
    public boolean mouseEntered(LightMouseEvent event) {
        return true;
    }

    @Override
    public boolean mouseExited(LightMouseEvent event) {
        return true;
    }

    @Override
    public boolean mouseMoved(LightMouseEvent event) {
        return true;
    }

    @Override
    public boolean mouseDragged(LightMouseEvent event) {
        return true;
    }

    @Override
    public boolean blockInput(Point point) {
        return true;
    }
}
