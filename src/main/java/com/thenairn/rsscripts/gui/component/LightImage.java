package com.thenairn.rsscripts.gui.component;

import com.thenairn.rsscripts.gui.LightComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Thomas Nairn on 16/03/2016.
 */
public class LightImage extends LightComponent {

    BufferedImage image;

    public LightImage(int x, int y, BufferedImage image) {
        super(x, y, image.getWidth(), image.getHeight());
        this.setOpaque(true);
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        g2d.drawImage(image, 0, 0, null);
    }
}
