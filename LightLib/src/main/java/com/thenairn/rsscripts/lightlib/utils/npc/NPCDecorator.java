package com.thenairn.rsscripts.lightlib.utils.npc;

import org.osbot.rs07.api.model.NPC;

public class NPCDecorator extends NPC {

    public NPCDecorator(NPC npc) {
        super(npc.accessor);
    }

}