package com.thenairn.rsscripts.lightlib.api;

import com.thenairn.rsscripts.lightlib.Inlustra;
import lombok.AccessLevel;
import lombok.Getter;
import org.osbot.rs07.Bot;
import org.osbot.rs07.script.API;
import org.osbot.rs07.script.MethodProvider;

import java.util.Arrays;

public abstract class LightAPI extends API {

    @Getter(AccessLevel.PROTECTED)
    private Inlustra inlustra;

    @Override
    public void initializeModule() {
    }

    public void initializeModule(Inlustra inlustra) {
        this.inlustra = inlustra;
        Arrays.stream(providers()).forEach((methodProvider) -> methodProvider.initializeModule(inlustra));
    }

    @Override
    public MethodProvider exchangeContext(Bot bot) {
        Arrays.stream(providers()).forEach((methodProvider) -> methodProvider.exchangeContext(bot));
        return super.exchangeContext(bot);
    }

    protected LightAPI[] providers() {
        return new LightAPI[0];
    }
}
