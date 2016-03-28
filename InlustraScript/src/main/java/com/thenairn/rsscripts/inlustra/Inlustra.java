package com.thenairn.rsscripts.inlustra;

import com.thenairn.rsscripts.lightlib.LightAPI;
import com.thenairn.rsscripts.lightlib.task.TaskedLightScript;
import com.thenairn.rsscripts.lightlib.utils.gui.LightGUI;
import com.thenairn.rsscripts.lightlib.utils.gui.containers.InventoryImageContainer;
import com.thenairn.rsscripts.lightlib.utils.world.GroundMonitor;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas Nairn on 22/03/2016.
 */
@Slf4j
@ScriptManifest(version = 13, author = "Inlustra", logo = "",
        name = "Ground Monitor 13", info = "The task toolkit.")
public class Inlustra extends TaskedLightScript {

    InventoryImageContainer inventory;
    GroundMonitor monitor = new GroundMonitor() {
        @Override
        protected void onAdd(GroundItem item) {
            updateGUI();
        }

        @Override
        protected void onRemove(GroundItem item) {
            updateGUI();
        }
    };

    @Override
    protected void setupGUI(LightGUI gui) {
        gui.add(inventory = new InventoryImageContainer());
    }

    @Override
    protected void addTasks() {
        addTask(monitor);
    }

    public void updateGUI() {
        int i = 0;
        List<GroundItem> items = monitor.getCachedItems();
        for (GroundItem item : items) {

            inventory.getSlot(i).setItem(item.getId(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    log.debug("Price of: " + item.getName() + " is : " + LightAPI.get().getItemApi().getPrice(item.getId()));
                }
            });
            if (i++ >= 27) break;
        }
    }

}
