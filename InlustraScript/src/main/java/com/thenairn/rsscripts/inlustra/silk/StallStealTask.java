package com.thenairn.rsscripts.inlustra.silk;

import com.thenairn.rsscripts.inlustra.SilkStealer;
import com.thenairn.rsscripts.lightlib.task.TaskedLightScript;
import com.thenairn.rsscripts.lightlib.task.type.ForegroundTask;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StallStealTask implements ForegroundTask {

    private Position favouredPosition;
    private TaskedLightScript script;
    private Area aroundStall;
    private Area stall;

    public StallStealTask(Area stall, Area aroundStall) {
        this.stall = stall;
        this.aroundStall = aroundStall;
    }

    @Override
    public boolean isRunning() {
        return !script.getInventory().isFull();
    }

    @Override
    public void onStart(TaskedLightScript script) {
        this.script = script;
    }

    @Override
    public void onStop(TaskedLightScript script) {

    }

    private Position stallHover;

    @Override
    public int update() {
        if (favouredPosition == null) {
            findFavouredPosition();
            return 20;
        }
        if (!script.getLocalWalker().walk(aroundStall, true)) {
            return 100;
        }
        Position pos = aroundStall.getRandomPosition();
        List<RS2Object> objects = script.getObjects().get(pos.getX(), pos.getY());
        if (objects.size() > 0) {
            RS2Object object = objects.get(0);
            if (script.getApi().getObjectAPI().canStealFrom(object)) {
                if (stallHover == null) {
                    stallHover = aroundStall.getRandomPosition();
                    stallHover.hover(script.getBot());
                }
                //script.getMouse().moveVerySlightly();
                return 10;
            }
            if (!script.myPlayer().isAnimating()) {
                object.interact("Steal-from");
                return 100;
            }
        }
        return 50;
    }

    public void findFavouredPosition() {
        Position favoured = null;
        int max = 0;
        for (Position position : aroundStall.getPositions()) {
            List<Player> players = script.getPlayers().get(position.getX(), position.getY());
            if (players.size() > max) {
                favoured = position;
            }
        }
        if (favoured == null) {
            favoured = aroundStall.getRandomPosition();
        }
        favouredPosition = favoured;
    }
}
