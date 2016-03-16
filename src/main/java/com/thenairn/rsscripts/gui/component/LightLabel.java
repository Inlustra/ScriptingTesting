package com.thenairn.rsscripts.gui.component;

import com.thenairn.rsscripts.gui.LightComponent;
import com.thenairn.rsscripts.resource.Fonts;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class LightLabel extends LightComponent {

    @Getter
    @Setter
    private String text;
    @Getter
    @Setter
    private Font font;

    public LightLabel(int x, int y, String text) {
        this(x, y, text, Fonts.RUNESCAPE_CHAT);
    }

    public LightLabel(int x, int y, String text, Font font) {
        super(x, y, 0, 0);
        this.font = font;
        FontRenderContext context = new FontRenderContext(getFont().getTransform(), true, true);
        Rectangle2D rect = font.getStringBounds(text, context);
        setWidth((int) rect.getWidth());
        setHeight((int) rect.getHeight());
        System.out.println(rect.getWidth()+" "+rect.getHeight());
        this.text = text;
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        g2d.setColor(this.getForeground());
        g2d.setFont(font);
        g2d.drawString(text, 0, 0);
    }
}
