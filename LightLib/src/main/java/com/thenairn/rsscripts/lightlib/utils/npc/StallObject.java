package com.thenairn.rsscripts.lightlib.utils.npc;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.osbot.rs07.api.model.RS2Object;

/**
 * Created by Thomas Nairn on 01/04/2016.
 */

@EqualsAndHashCode(callSuper = true)
@Data(staticConstructor = "from")
public class StallObject extends RS2ObjectDecorator {
    public StallObject(RS2Object object) {
        super(object);
    }
}
