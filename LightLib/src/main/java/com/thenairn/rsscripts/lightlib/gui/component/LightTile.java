package com.thenairn.rsscripts.lightlib.gui.component;

import com.thenairn.rsscripts.lightlib.gui.LightComponent;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;

/**
 * Created by thoma on 28/03/2016.
 */
public class LightTile extends LightComponent {

    private Position position;
    private MethodProvider provider;

    public LightTile(Color color, MethodProvider provider, Position position) {
        super(0, 0, 0, 0);
        this.position = position;
        this.provider = provider;
        setForeground(color);
    }

    @Override
    public int getWidth() {
        return getParent().getWidth();
    }

    @Override
    public int getHeight() {
        return getParent().getHeight();
    }

    @Override
    public void paintComponent(Graphics2D g2d) {
        Polygon poly = position.getPolygon(provider.getBot());
        if (poly != null && poly.npoints > 0) {
            g2d.fillPolygon(poly.xpoints, poly.ypoints, poly.npoints);
        }
    }
}
