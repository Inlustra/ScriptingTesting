package com.thenairn.rsscripts.utils.items;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Robin Nairn on 12/03/2016.
 */
public class ItemUtils {



    private static Map<Integer, RunescapeItem> items = new HashMap<>();

    private static List<RunescapeItem> loadItemList() {
        List<RunescapeItem> runescapeItems = new ArrayList<>();
        URL url = null;
        try {
            url = new URL("https://gist.githubusercontent.com/anonymous/a1aa9894c1acc0c1c201/raw/b8bd35ed57a9cedfb1dc2379212ac79faa979136/IDs.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] item = inputLine.split(inputLine);
                runescapeItems.add(new RunescapeItem(Integer.parseInt(item[1]), item[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return runescapeItems;
    }
}
