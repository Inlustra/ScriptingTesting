package com.thenairn.rsscripts.lightlib;

import com.thenairn.rsscripts.lightlib.utils.items.ItemAPI;
import com.thenairn.rsscripts.lightlib.utils.misc.SimpleTimer;
import com.thenairn.rsscripts.lightlib.utils.world.GrandExchange;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.osbot.rs07.script.API;

@Slf4j
public class LightAPI extends API {

    private static LightAPI _instance;

    public static LightAPI get() {
        return _instance == null ? _instance = new LightAPI() : _instance;
    }

    @Getter
    private SimpleTimer elapsed;
    @Getter
    private ItemAPI itemApi;
    @Getter
    private GrandExchange ge;

    @Override
    public void initializeModule() {
        elapsed = new SimpleTimer();
        itemApi = new ItemAPI().init();
        ge = new GrandExchange(this);
        log.debug("LightAPI took " + elapsed.getFormatted("s") + " seconds to start");
    }

}
