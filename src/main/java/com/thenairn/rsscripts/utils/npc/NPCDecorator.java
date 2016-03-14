package com.thenairn.rsscripts.utils.npc;

import org.osbot.rs07.api.model.NPC;

public class NPCDecorator extends NPC {

    public NPCDecorator(NPC npc) {
        super(npc.accessor);
    }

}