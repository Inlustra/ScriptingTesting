package com.thenairn.rsscripts.inlustra;

import com.thenairn.rsscripts.lightlib.gui.LightGUI;
import com.thenairn.rsscripts.lightlib.gui.component.LightTilePainter;
import com.thenairn.rsscripts.lightlib.task.TaskedLightScript;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import static com.thenairn.rsscripts.lightlib.api.ObjectAPI.canStealFrom;


@Slf4j
@ScriptManifest(version = 13, author = "Inlustra", logo = "",
        name = "SilkStealer15", info = "The task toolkit.")
public class SilkStealer extends TaskedLightScript implements MouseListener {
    private LightTilePainter painter;
    private boolean settingUp = true;
    private Area stall;
    private List<Position> aroundStall;

    @Override
    protected void setupGUI(LightGUI gui) {
        gui.add(this.painter = new LightTilePainter(this));
    }

    @Override
    protected void addTasks() {
        getBot().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!settingUp)
            return;
        setup(getApi().getWorldAPI().getPositionUnderMouse(e.getPoint()));
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
        this.stall = getApi().getObjectAPI().getObjectArea(object);
        this.aroundStall = getApi().getObjectAPI().getObjectPositions(object, 1);
        //addTask(new StallStealTask(this.stall, this.aroundStall)).addTask(new BankItemsTask());
        painter.addTile(Color.green, this.stall.getPositions().toArray(new Position[stall.getPositions().size()]));
        painter.addTile(new Color(128, 128, 128, 128), this.aroundStall);
        settingUp = false;
        getBot().removeMouseListener(this);
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
