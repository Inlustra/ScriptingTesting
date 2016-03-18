package com.thenairn.rsscripts.utils.gui.event;

import com.thenairn.rsscripts.utils.gui.LightComponent;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by thoma on 15/03/2016.
 */
public class LightMouseEvent extends MouseEvent {

    public LightMouseEvent(MouseEvent event, LightComponent source) {
        super((Component) event.getSource(), event.getID(), event.getWhen(),
                event.getModifiers(), event.getX() - source.getX(), event.getY() - source.getY(),
                event.getClickCount(), event.isPopupTrigger(), event.getButton());
    }

}
