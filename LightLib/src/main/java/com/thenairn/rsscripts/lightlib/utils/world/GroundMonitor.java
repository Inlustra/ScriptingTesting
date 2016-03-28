package com.thenairn.rsscripts.lightlib.utils.world;

import com.thenairn.rsscripts.lightlib.LightScript;
import com.thenairn.rsscripts.lightlib.task.TaskedLightScript;
import com.thenairn.rsscripts.lightlib.task.type.BackgroundTask;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.script.MethodProvider;

import java.util.*;

@Slf4j
public abstract class GroundMonitor implements BackgroundTask {

    @Getter
    private List<GroundItem> items;
    private Comparator<GroundItem> comparator;
    private MethodProvider methodProvider;
    private boolean stopped = false;

    public GroundMonitor() {
    }

    public GroundMonitor(MethodProvider provider) {
        this.methodProvider = provider;
    }

    public GroundMonitor(Comparator<GroundItem> comparator) {
        this.comparator = comparator;
    }

    private List<GroundItem> getGroundItems() {
        return methodProvider.getGroundItems().getAll();
    }

    public List<GroundItem> getCachedItems() {
        ArrayList<GroundItem> list = new ArrayList<>(this.items);
        Collections.sort(list, comparator);
        return list;
    }

    @Override
    public int update() {
        List<GroundItem> ground = getGroundItems();
        if (ground.size() == this.items.size())
            //For Simplicity, if the amount of items doesn't change, don't bother comparing
            //Could get really unlucky in that an item is removed and added in the same tick
            //Call update more Yo'
            return 10;
        Set<GroundItem> additions = new HashSet<>(ground);
        additions.removeAll(this.items);
        for (GroundItem item : additions) {
            this.items.add(item);
            onAdd(item);
        }
        Set<GroundItem> removals = new HashSet<>(items);
        removals.removeAll(ground);
        for (GroundItem item : removals) {
            this.items.remove(item);
            onRemove(item);
        }
        return 10;
    }


    @Override
    public boolean stopForeground() {
        return false;
    }


    public void stop() {
        stopped = true;
    }

    @Override
    public boolean isRunning() {
        return !stopped;
    }

    @Override
    public void onStop(TaskedLightScript script) {

    }

    @Override
    public void onStart(TaskedLightScript script) {
        if (this.comparator == null) {
            this.comparator = new DistanceGroundItemComparator(script);
        }
        this.items = new ArrayList<>();
        this.methodProvider = script;
    }

    protected abstract void onAdd(GroundItem item);

    protected abstract void onRemove(GroundItem item);

    public static class DistanceGroundItemComparator implements Comparator<GroundItem> {

        MethodProvider methodProvider;

        public DistanceGroundItemComparator(MethodProvider methodProvider) {
            this.methodProvider = methodProvider;
        }

        @Override
        public int compare(GroundItem o1, GroundItem o2) {
            int o1d = methodProvider.myPlayer().getPosition().distance(o1.getPosition());
            int o2d = methodProvider.myPlayer().getPosition().distance(o2.getPosition());
            return o1d - o2d;
        }
    }

}