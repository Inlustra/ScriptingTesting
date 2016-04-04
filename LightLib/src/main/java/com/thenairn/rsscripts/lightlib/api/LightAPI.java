package com.thenairn.rsscripts.lightlib.api;

import com.thenairn.rsscripts.lightlib.Inlustra;
import com.thenairn.rsscripts.lightlib.utils.misc.SimpleTimer;
import lombok.AccessLevel;
import lombok.Getter;
import org.osbot.rs07.Bot;
import org.osbot.rs07.script.API;
import org.osbot.rs07.script.MethodProvider;

import java.util.Arrays;

public abstract class LightAPI extends API implements LightProvider {

    private LightProvider parent;

    public LightProvider getParent() {
        if (parent == null)
            throw new NullPointerException("LightProvider content not yet exchanged.");
        return parent;
    }

    @Override
    public void initializeModule() {
    }

    public void exchangeContent(LightProvider provider) {
        this.parent = provider;
    }

    public ItemAPI getItemAPI() {
        return getParent().getItemAPI();
    }

    public NetworkAPI getNetworkAPI() {
        return getParent().getNetworkAPI();
    }

    public ObjectAPI getObjectAPI() {
        return getParent().getObjectAPI();
    }

    public WorldAPI getWorldAPI() {
        return getParent().getWorldAPI();
    }

    public Inlustra inlustra() {
        return getParent().inlustra();
    }
}
