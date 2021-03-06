package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * Created by Thomas Nairn on 21/03/2016.
 */
public class OSBotLoggerFactory implements ILoggerFactory {
    @Override
    public Logger getLogger(String name) {
        return new OSBotLogger(name);
    }

}
