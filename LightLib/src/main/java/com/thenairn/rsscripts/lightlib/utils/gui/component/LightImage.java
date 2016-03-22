package com.thenairn.rsscripts.lightlib.utils.gui.component;

import com.thenairn.rsscripts.lightlib.utils.gui.LightComponent;

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
}
