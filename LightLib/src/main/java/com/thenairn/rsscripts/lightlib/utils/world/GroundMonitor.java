package com.thenairn.rsscripts.lightlib.utils.world;

import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.script.API;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public abstract class GroundMonitor {

    private Set<GroundItem> items;
    private Comparator<GroundItem> comparator;
    private API api;

    public GroundMonitor(API api) {
        this(api, new DistanceGroundItemComparator(api));
    }

    public GroundMonitor(API api, Comparator<GroundItem> comparator) {
        this.api = api;
        this.comparator = comparator;
        this.items = new TreeSet<>(this.comparator);
    }

    private Set<GroundItem> getGroundItems() {
        return new HashSet<>(api.getGroundItems().getAll());
    }

    public Set<GroundItem> getCachedItems() {
        return new HashSet<>(this.items);
    }

    public void update() {
        Set<GroundItem> ground = getGroundItems();
        if (ground.size() == this.items.size())
            //For Simplicity, if the amount of items doesn't change, don't bother comparing
            //Could get really unlucky in that an item is removed and added in the same tick
            //Call update more Yo'
            return;
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
    }

    protected abstract void onAdd(GroundItem item);

    protected abstract void onRemove(GroundItem item);

    private static class DistanceGroundItemComparator implements Comparator<GroundItem> {

        API api;

        public DistanceGroundItemComparator(API api) {
            this.api = api;
        }

        @Override
        public int compare(GroundItem o1, GroundItem o2) {
            int o1d = api.myPlayer().getPosition().distance(o1.getPosition());
            int o2d = api.myPlayer().getPosition().distance(o1.getPosition());
            return o1d - o2d;
        }
    }

}