package com.thenairn.rsscripts.lightlib.gui.component;

import com.thenairn.rsscripts.lightlib.gui.LightComponent;
import com.thenairn.rsscripts.lightlib.gui.event.LightMouseEvent;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Thomas Nairn on 16/03/2016.
 */
public class LightImage extends LightComponent {

    @Getter
    @Setter
    private BufferedImage image;

    public LightImage(int x, int y, int width, int height, BufferedImage image) {
        super(x, y, width, height);
        this.setOpaque(false);
        this.image = image;
    }


    public LightImage(int x, int y, BufferedImage image) {
        this(x, y, image.getWidth(), image.getHeight(), image);
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        if (image != null)
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
