package com.thenairn.rsscripts.utils.log;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * Created by Thomas Nairn on 21/03/2016.
 */
public class OSBotLoggerBinder implements LoggerFactoryBinder {

    /**
     * The unique instance of this class.
     */
    private static final OSBotLoggerBinder SINGLETON = new OSBotLoggerBinder();

    /**
     * Return the singleton of this class.
     *
     * @return the StaticLoggerBinder singleton
     */
    public static final OSBotLoggerBinder getSingleton() {
        return SINGLETON;
    }

    /**
     * Declare the version of the SLF4J API this implementation is compiled
     * against. The value of this field is usually modified with each release.
     */
    // to avoid constant folding by the compiler, this field must *not* be final
    public static String REQUESTED_API_VERSION = "1.7.19"; // !final

    private static final String loggerFactoryClassStr = OSBotLoggerFactory.class.getName();

    /**
     * The ILoggerFactory instance returned by the {@link #getLoggerFactory}
     * method should always be the same object
     */
    private final ILoggerFactory loggerFactory;

    private OSBotLoggerBinder() {
        loggerFactory = new OSBotLoggerFactory();
    }

    public ILoggerFactory getLoggerFactory() {
        return loggerFactory;
    }

    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }
}
