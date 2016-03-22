package com.thenairn.rsscripts.lightlib.utils.items;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Robin Nairn on 12/03/2016.
 */
public class ItemAPI {

    private static final String ITEM_LIST_URL = "https://gist.githubusercontent.com/anonymous/a1aa9894c1acc0c1c201/raw/b8bd35ed57a9cedfb1dc2379212ac79faa979136/IDs.txt";
    private Map<Integer, RunescapeItem> items;

    public ItemAPI init() {
        if (items != null) {
            System.out.println("Already initialized the ItemAPI");
            return this;
        }
        items = new HashMap<>();
        List<RunescapeItem> items = loadItemList();
        for (RunescapeItem item : items) {
            this.items.put(item.getId(), item);
        }
        return this;
    }

    public RunescapeItem get(int id) {
        if (items == null)
            throw new NullPointerException("ItemAPI not yet initialized, call init() first.");
        return items.get(id);
    }

    public RunescapeItemDetails getDetails(int id) {
        RunescapeItem item = get(id);
        if (item == null)
            throw new NullPointerException("RunescapeItem: " + id + ", not yet loaded");
        return item.getDetails();
    }

    public int getPrice(int id) {
        RunescapeItemDetails details = getDetails(id);
        if (details == null) {
            return -1;
        }
        return details.getCurrent().getPrice();
    }

    private List<RunescapeItem> loadItemList() {
        List<RunescapeItem> runescapeItems = new ArrayList<>();
        try {
            URL url = new URL(ITEM_LIST_URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.isEmpty()) continue;
                String[] item = inputLine.split(" - ");
                try {
                    int itemID = Integer.parseInt(item[item.length - 1]);
                    StringBuilder itemName = new StringBuilder();
                    for (int i = 0; i < item.length - 2; i++) {
                        itemName.append(item[i]);
                    }
                    runescapeItems.add(new RunescapeItem(itemID, itemName.toString()));
                } catch (Exception e) {
                    System.err.println(e.getMessage() + " | " + inputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return runescapeItems;
    }
}
