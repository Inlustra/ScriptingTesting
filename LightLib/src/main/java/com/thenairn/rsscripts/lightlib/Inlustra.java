package com.thenairn.rsscripts.lightlib;

import com.thenairn.rsscripts.lightlib.api.*;
import com.thenairn.rsscripts.lightlib.utils.misc.SimpleTimer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Inlustra extends LightAPI {

    @Getter
    private SimpleTimer elapsed;
    @Getter
    private ItemAPI itemAPI;
    @Getter
    private GrandExchangeAPI grandExchangeAPI;
    @Getter
    private ObjectAPI objectAPI;
    @Getter
    private WorldAPI worldAPI;

    public Inlustra() {
        elapsed = new SimpleTimer();
        itemAPI = new ItemAPI();
        grandExchangeAPI = new GrandExchangeAPI();
        objectAPI = new ObjectAPI();
        worldAPI = new WorldAPI();
    }

    @Override
    protected LightAPI[] providers() {
        return new LightAPI[]{itemAPI, grandExchangeAPI, objectAPI, worldAPI};
    }

    @Override
    public void initializeModule() {
        super.initializeModule(this);
        log.debug("Inlustra took " + elapsed.getFormatted("s") + " seconds to start");
    }

}
