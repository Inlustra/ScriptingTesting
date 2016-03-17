package com.thenairn.rsscripts.gui;

/**
 * Created by Thomas Nairn on 17/03/2016.
 */
public class DebugContainer extends LightContainer {

    public DebugContainer() {
        super(0, 0, 0, 0);


    }

    @Override
    public int getHeight() {
        return getParent().getHeight();
    }

    @Override
    public int getWidth() {
        return getParent().getWidth();
    }
}
