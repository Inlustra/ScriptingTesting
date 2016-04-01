package com.thenairn.rsscripts.lightlib.utils.npc;

import lombok.experimental.Delegate;
import org.osbot.rs07.api.model.RS2Object;

public class RS2ObjectDecorator {

    @Delegate
    private final RS2Object object;

    public RS2ObjectDecorator(RS2Object object) {
        this.object = object;
    }

}
