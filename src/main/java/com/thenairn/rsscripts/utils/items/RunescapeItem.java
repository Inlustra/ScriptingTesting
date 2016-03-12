package com.thenairn.rsscripts.utils.items;

import com.thenairn.rsscripts.utils.misc.Cached;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class RunescapeItem {

    private String name;
    private int id;

    private String description;

    private Cached<RunescapeItemDetails> details = new Cached<RunescapeItemDetails>() {
        @Override
        protected RunescapeItemDetails load() {
            return null;
        }
    };


    private Cached<BufferedImage> icon = new Cached<BufferedImage>() {
        @Override
        protected BufferedImage load() {
            try {
                return ImageIO.read(new URL("http://cdn.rsbuddy.com/items/" + RunescapeItem.this.id + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public RunescapeItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BufferedImage getIcon() {
        return this.icon.get();
    }

    public RunescapeItemDetails getDetails() {
        return this.details.get();
    }

}