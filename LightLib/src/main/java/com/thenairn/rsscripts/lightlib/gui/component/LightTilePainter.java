package com.thenairn.rsscripts.lightlib.gui.component;

import com.thenairn.rsscripts.lightlib.gui.containers.WorldContainer;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by thoma on 28/03/2016.
 */
public class LightTilePainter extends WorldContainer {

    private final Map<Position, LightTile> tiles = new HashMap<>();
    private final MethodProvider provider;
    private Color defaultColor = new Color(102, 175, 64, 50);

    public LightTilePainter(MethodProvider provider) {
        this.provider = provider;
    }

    public void addTile(Color color, Position... positions) {
        addTile(color, Arrays.asList(positions));
    }

    public void addTile(Color color, Collection<Position> positions) {
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

    public void addTile(List<Position> positions) {
        addTile(defaultColor, positions);
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
        remove(tiles.values());
        tiles.clear();
    }

    @Override
    public boolean blockInput(Point e) {
        super.blockInput(e);
        return false;
    }
}
