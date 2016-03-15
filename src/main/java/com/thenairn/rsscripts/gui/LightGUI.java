package com.thenairn.rsscripts.gui;

import com.thenairn.rsscripts.gui.component.LightComponent;
import com.thenairn.rsscripts.gui.component.LightContainer;
import com.thenairn.rsscripts.script.LightAPI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by thoma on 15/03/2016.
 */
public class LightGUI extends LightContainer implements MouseListener {

    private LightAPI api;

    public LightGUI(LightAPI api) {
        super(0, 0, api.getBot().getCanvas().getWidth(),
                api.getBot().getCanvas().getHeight());
        api.getBot().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
