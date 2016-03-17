package com.thenairn.rsscripts.script;

import com.thenairn.rsscripts.gui.LightGUI;
import org.osbot.rs07.script.API;

import java.awt.*;

/**
 * Created by thoma on 15/03/2016.
 */
public class LightAPI extends API {

    LightGUI gui;

    public LightAPI() {
        this.gui = new LightGUI(this);
    }

    @Override
    public void initializeModule() {
    }

    void onPaint(Graphics2D graphics2D) {


    }


}
