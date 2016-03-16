package com.thenairn.rsscripts.gui.util;

import com.thenairn.rsscripts.gui.LightComponent;

import java.util.Iterator;

/**
 * Created by Thomas Nairn on 16/03/2016.
 */
public class ParentIterator implements Iterator<LightComponent> {

    private LightComponent source;

    public ParentIterator(LightComponent source) {
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return source.getParent() != null;
    }

    @Override
    public LightComponent next() {
        return source = source.getParent();
    }


}
