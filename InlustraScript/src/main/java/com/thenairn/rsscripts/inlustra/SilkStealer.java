package com.thenairn.rsscripts.inlustra;

import com.thenairn.rsscripts.inlustra.silk.BankItemsTask;
import com.thenairn.rsscripts.inlustra.silk.StallStealTask;
import com.thenairn.rsscripts.lightlib.task.TaskedLightScript;
import com.thenairn.rsscripts.lightlib.utils.gui.LightGUI;
import com.thenairn.rsscripts.lightlib.utils.gui.component.LightTilePainter;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@ScriptManifest(version = 13, author = "Inlustra", logo = "",
        name = "SilkStealer6", info = "The task toolkit.")
public class SilkStealer extends TaskedLightScript implements MouseListener {
    private LightTilePainter painter;
    private boolean settingUp = true;
    private Area stall;
    private Area aroundStall;

    @Override
    protected void setupGUI(LightGUI gui) {
        gui.add(this.painter = new LightTilePainter(this));
    }

    @Override
    protected void addTasks() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!settingUp)
            return;
        setup(getPositionUnderMouse(e.getPoint()));
    }

    private void setup(Position positionUnderMouse) {
        List<RS2Object> objects = getObjects().get(positionUnderMouse.getX(), positionUnderMouse.getY());
        if (objects.size() <= 0)
            return;
        for (RS2Object object : objects) {
            if (!canStealFrom(object))
                continue;
            setup(object);
        }
    }

    private void setup(RS2Object object) {
        log.debug("Stall x: %d y: %d", object.getX(), object.getY());
        List<Position> stallPositions = new ArrayList<>();
        for (int x = object.getX(); x < object.getX() + object.getSizeX(); x++) {
            for (int y = object.getY(); y < object.getY() + object.getSizeY(); y++) {
                stallPositions.add(new Position(x, y, object.getZ()));
            }
        }
        log.debug("There are %d positions on this stall", stallPositions.size());
        this.stall = new Area(stallPositions.toArray(new Position[stallPositions.size()]));
        List<Position> positionsAroundStall = new ArrayList<>();
        for (int x = object.getX() - 1; x < object.getX() + object.getSizeX() + 1; x++) {
            for (int y = object.getY() - 1; y < object.getY() + object.getSizeY() + 1; y++) {
                Position p = new Position(x, y, object.getZ());
                if (stallPositions.contains(p)) continue;
                positionsAroundStall.add(p);
            }
        }
        log.debug("There are %d positions around this stall", positionsAroundStall.size());
        this.aroundStall = new Area(positionsAroundStall.toArray(new Position[positionsAroundStall.size()]));
        addTask(new StallStealTask(this.stall, this.aroundStall)).addTask(new BankItemsTask());
        painter.addTile(Color.green, stallPositions.toArray(new Position[stallPositions.size()]));
        painter.addTile(new Color(128, 128, 128, 128), positionsAroundStall.toArray(new Position[positionsAroundStall.size()]));
        settingUp = false;
    }

    public static boolean canStealFrom(RS2Object object) {
        for (String str : object.getActions()) {
            if (str.toLowerCase().contains("steal"))
                return true;
        }
        return false;
    }

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
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
