package com.thenairn.rsscripts.lightlib.api;

import com.thenairn.rsscripts.lightlib.utils.npc.StallObject;
import org.osbot.rs07.api.map.Position;

import java.awt.*;

/**
 * Created by Thomas Nairn on 01/04/2016.
 */
public class WorldAPI extends LightAPI {


    public Position getPositionUnderMouse(Point mousePosition) {
        for (int x = 0; x < 104; x++) {
            for (int y = 0; y < 104; y++) {
                Position pos = new Position(getMap().getBaseX() + x, getMap().getBaseY() + y, getMap().getPlane());
                if (pos.isVisible(getBot()) && pos.getPolygon(getBot()).contains(mousePosition)) {
                    return pos;
                }
            }
        }

        return null;
    }

    @Override
    public void initializeModule() {
        StallObject s = new StallObject(null);
    }
}
