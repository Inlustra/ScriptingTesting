package com.thenairn.rsscripts.lightlib.gui.component;

import com.thenairn.rsscripts.lightlib.gui.LightComponent;
import com.thenairn.rsscripts.lightlib.utils.misc.Cached;
import com.thenairn.rsscripts.lightlib.resource.Fonts;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class LightLabel extends LightComponent {

    @Getter
    @Setter
    private String text;
    @Getter

    private Font font;
    @Getter
    private float fontSize;
    @Getter
    @Setter
    private int margin;
    private boolean invalidate = false;

    public LightLabel(int x, int y, String text) {
        this(x, y, text, Fonts.RUNESCAPE_CHAT);
    }

    public LightLabel(int x, int y, String text, Font font) {
        this(x, y, text, font, 15);
    }

    public LightLabel(int x, int y, String text, Font font, float size) {
        this(x, y, text, font, size, 10);
    }

    public LightLabel(int x, int y, String text, Font font, float size, int margin) {
        super(x, y, 0, 0);
        this.font = font;
        this.fontSize = size;
        this.text = text;
        this.margin = margin;
    }

    private Cached<Dimension> textDimensions = new Cached<Dimension>() {
        @Override
        protected Dimension load() {
            font = getFont().deriveFont(getFontSize());
            FontMetrics metrics = new Canvas().getFontMetrics(font);
            return new Dimension(metrics.stringWidth(getText()), metrics.getHeight());
        }
    };

    @Override
    public int getWidth() {
        return (int) (textDimensions.get().getWidth() + getMargin());
    }

    @Override
    public int getHeight() {
        return (int) (textDimensions.get().getHeight() + getMargin());
    }

    public void setFont(Font font) {
        this.font = font;
        textDimensions.revalidate();
    }

    public void setFontSize(float size) {
        this.fontSize = size;
        textDimensions.revalidate();
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        Dimension dimensions = textDimensions.get();
        g2d.setPaint(this.getForeground());
        g2d.setFont(font);
        int x = (int) ((getWidth() - dimensions.getWidth()) / 2);
        int y = (int) (((getHeight() - dimensions.getHeight()) / 2) + g2d.getFontMetrics().getAscent());
        //int x = 0;
        //int y = g2d.getFontMetrics().getAscent();
        g2d.drawString(text, x, y);
    }
}
