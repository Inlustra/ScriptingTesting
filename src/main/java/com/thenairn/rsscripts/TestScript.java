package com.thenairn.rsscripts;

import com.thenairn.rsscripts.script.LightScript;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;

@ScriptManifest(version = 1, author = "Inlustra", logo = "", name = "TestScript", info = "Showing you how to use the events system in a script.")
public class TestScript extends LightScript {

    @Override
    public void onPaint(Graphics2D g2d) {
        super.onPaint(g2d);
    }

    @Override
    public int onLoop() throws InterruptedException {
        return 0;
    }
}
