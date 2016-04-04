package com.thenairn.rsscripts.lightlib.gui.containers;

import com.thenairn.rsscripts.lightlib.Inlustra;
import com.thenairn.rsscripts.lightlib.api.ItemAPI;
import com.thenairn.rsscripts.lightlib.gui.component.LightImage;
import com.thenairn.rsscripts.lightlib.gui.event.LightMouseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.ActionListener;

@Slf4j
public class InventoryImageContainer extends InventoryContainer {

    private ItemSlot[] itemSlots = new ItemSlot[28];
    private ItemAPI itemAPI;

    public InventoryImageContainer(ItemAPI itemAPI) {
        super();
        this.itemAPI = itemAPI;
        this.opaque = true;
        this.background = Color.BLACK;
        for (int i = 0; i < itemSlots.length; i++) {
            int x = (int) ((45 * Math.floor(i % 4))) + 8;
            int y = (int) ((36 * Math.floor(i / 4)) + 8);
            add(itemSlots[i] = new ItemSlot(x, y));
        }
    }

    public ItemSlot getSlot(int position) {
        return itemSlots[position];
    }

    public ItemSlot getSlot(int row, int col) {
        return itemSlots[row * col];
    }

    public class ItemSlot extends LightImage {
        @Getter
        private int itemId;
        @Getter
        @Setter
        private ActionListener onClick;


        public ItemSlot(int x, int y) {
            super(x, y, 36, 38, null);
        }

        public void setItem(int id, ActionListener onClick) {
            this.itemId = id;
            this.onClick = onClick;
            this.setImage(itemAPI.get(id).getIcon());
        }

        @Override
        public boolean mouseClicked(LightMouseEvent event) {
            if (this.onClick != null) {
                this.onClick.actionPerformed(null);
                return true;
            }
            return super.mouseClicked(event);
        }

        @Override
        public boolean blockInput(Point point) {
            return true;
        }
    }
}
