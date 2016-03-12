package com.thenairn.rsscripts.utils.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.thenairn.rsscripts.utils.items.ItemAPI;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.script.Script;


public abstract class GroundMonitor {
    //My implementation filters out items less than price x, but I removed because it was specific to my needs
    private ArrayList<GroundItem> cache = new ArrayList<GroundItem>();
    private Script ctx;

    public GroundMonitor(Script ctx, int valueThreshold) {
        updateContext(ctx);
        update();
    }

    public void updateContext(Script ctx) {
        this.ctx = ctx;
    }

    public abstract void onChange();

    public boolean hasChanged() {
        return getChanges().length > 0;
    }

    public void update() {
        for (GroundItem item : ctx.getGroundItems().getAll()) {
            if (item != null) {
                cache.add(item);
            }
        }
    }

    public GroundItem[] getChanges() {
        List<GroundItem> items = new ArrayList<GroundItem>();
        ArrayList<GroundItem> ci = new ArrayList<GroundItem>();
        int changes = 0;
        for(GroundItem q : ctx.getGroundItems().getAll()){
            if(q != null){
                items.add(q);
            }
        }

        for (GroundItem item : items) {
            if (item != null) {
                int id = item.getId();
                if (!contains(ci, id) && !cached(item)) {
                    ci.add(item);
                    changes++;
                }
            }
        }

        Collections.sort(ci, new Comparator<GroundItem>(){
            @Override
            public int compare(GroundItem o1, GroundItem o2) {
                int d0 = ItemAPI.getPrice(o1.getId()); //Use distance instead of price
                int d1 = ItemAPI.getPrice(o2.getId()); //Use distance instead of price
                return (d0 < d1 ? -1 :             //It will return the closer one first
                        (d0 == d1 ? 0 : 1));
            }

        });

        return Arrays.copyOf(ci.toArray(new GroundItem[ci.size()]), changes);
    }

    private boolean cached(GroundItem item){
        return cache.contains(item);
    }

    private boolean contains(ArrayList<GroundItem> list, int id) {
        for (GroundItem i : list) {
            if (i == null)
                continue;
            if (i.getId() == id) {
                return true;
            }
        }
        return false;
    }

}