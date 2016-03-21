package com.thenairn.rsscripts;

import com.thenairn.rsscripts.script.LightScript;
import com.thenairn.rsscripts.script.selector.InnerScriptDescriptor;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(version = 10, author = "Inlustra", logo = "", name = "Light Up The Dark10", info = "The script toolkit.")
public class Inlustra extends LightScript {

    @Override
    public InnerScriptDescriptor[] getDescriptors() {
        return new InnerScriptDescriptor[]{
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build(),
                InnerScriptDescriptor.of(null).name("Test script1").item(579).build()
        };
    }
}
