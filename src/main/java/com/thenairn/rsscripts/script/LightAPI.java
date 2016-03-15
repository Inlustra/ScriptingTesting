package com.thenairn.rsscripts.script;

import org.osbot.rs07.canvas.paint.Painter;
import org.osbot.rs07.script.API;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by thoma on 15/03/2016.
 */
public class LightAPI extends API {

    private List<Painter> lightPainters = new LinkedList<>();

    public void add(Painter painter) {
        this.lightPainters.add(painter);
    }

    @Override
    public void initializeModule() {
    }

    void onPaint(Graphics2D graphics2D) {
        lightPainters.forEach((painter) -> painter.onPaint(graphics2D));
    }
}
