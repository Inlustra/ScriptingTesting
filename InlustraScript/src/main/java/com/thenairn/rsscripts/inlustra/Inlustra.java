package com.thenairn.rsscripts.inlustra;

import com.thenairn.rsscripts.lightlib.LightScript;
import com.thenairn.rsscripts.lightlib.task.selector.TaskDescriptor;
import org.osbot.rs07.script.ScriptManifest;

/**
 * Created by Thomas Nairn on 22/03/2016.
 */
@ScriptManifest(version = 13, author = "Inlustra", logo = "", name = "Light it up5", info = "The task toolkit.")
public class Inlustra extends LightScript {

    @Override
    public TaskDescriptor[] getDescriptors() {
        return new TaskDescriptor[]{
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build(),
                TaskDescriptor.of(null).name("Test script1").item(579).build()
        };
    }
}
