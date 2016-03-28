package com.thenairn.rsscripts.lightlib.utils.gui.component;

import com.thenairn.rsscripts.lightlib.utils.gui.containers.WorldContainer;
import javafx.geometry.Pos;
import org.osbot.P;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoma on 28/03/2016.
 */
public class LightTilePainter extends WorldContainer {

    private Color defaultColor = new Color(102, 175, 64, 50);
    private MethodProvider provider;

    Map<Position, LightTile> tiles = new HashMap<>();

    public LightTilePainter(MethodProvider provider) {
        this.provider = provider;
    }

    public void addTile(Color color, Position... positions) {
        for (Position position : positions) {
            if (position == null) continue;
            LightTile tile = new LightTile(color, provider, position);
            add(tile);
            LightTile previous = tiles.put(position, tile);
            if (previous != null) {
                remove(previous);
            }
        }
    }

    public void addTile(Position... positions) {
        addTile(defaultColor, positions);
    }

    public void removeTile(Position... positions) {
        for (Position position : positions) {
            remove(tiles.remove(position));
        }
    }

    public void clear() {
        remove(tiles.values().toArray(new LightTile[tiles.size()]));
        tiles.clear();
    }

    @Override
    public boolean blockInput(Point e) {
        super.blockInput(e);
        return false;
    }
}
