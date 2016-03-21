package com.thenairn.rsscripts.script;

import com.thenairn.rsscripts.utils.items.ItemAPI;
import com.thenairn.rsscripts.utils.misc.SimpleTimer;
import org.osbot.rs07.script.API;

/**
 * Created by thoma on 15/03/2016.
 */
public class LightAPI extends API {

    private static LightAPI _instance;

    public static LightAPI get() {
        return _instance == null ? _instance = new LightAPI() : _instance;
    }

    SimpleTimer timer = new SimpleTimer();

    public LightAPI() {
        ItemAPI.init();
    }

    @Override
    public void initializeModule() {
    }

}
