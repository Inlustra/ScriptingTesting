package com.thenairn.rsscripts.lightlib.utils.object;

import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoma on 30/03/2016.
 */
@Slf4j
public class ObjectAPI {
    private MethodProvider provider;

    public ObjectAPI(MethodProvider provider) {
        this.provider = provider;
    }

    public List<Position> getObjectPositions(RS2Object object) {
        int sx = object.getSizeX();
        int sy = object.getSizeY();
        List<Position> objectPositions = new ArrayList<>(sx * sy);
        for (int x = object.getX(); x < object.getX() + sx; x++) {
            for (int y = object.getY(); y < object.getY() + sy; y++) {
                objectPositions.add(new Position(x, y, object.getZ()));
            }
        }
        log.debug("There are %d positions on the %s", objectPositions.size(), object.getName());
        return objectPositions;
    }

    public Area getObjectArea(RS2Object object) {
        return positionsToArea(getObjectPositions(object));
    }

    public List<Position> getObjectContourPositions(RS2Object object, int around) {
        int sx = object.getSizeX();
        int sy = object.getSizeY();
        int startX = object.getX() - around;
        int startY = object.getY() - around;
        int endX = object.getX() + sx + around;
        int endY = object.getY() + sx + around;
        List<Position> positionsAroundObject = new ArrayList<>();
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                if (y >= object.getY() && y < object.getY() + sy
                        && x >= object.getX() && x < object.getX() + sx) {
                    y += sy - 1;
                    continue;
                }
                Position p = new Position(x, y, object.getZ());
                positionsAroundObject.add(p);
            }
        }
        log.debug("There are %d positions around the %s", positionsAroundObject.size(), object.getName());
        return positionsAroundObject;
    }

    public Area getObjectContourArea(RS2Object object, int around) {
        return positionsToArea(getObjectContourPositions(object, around));
    }

    private Area positionsToArea(List<Position> positionsAroundObject) {
        return new Area(positionsAroundObject.toArray(new Position[positionsAroundObject.size()]));
    }
}
