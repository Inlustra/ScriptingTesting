package org.slf4j.impl;

import lombok.SneakyThrows;
import org.osbot.utility.Logger;
import org.slf4j.helpers.MarkerIgnoringBase;

/**
 * Created by Thomas Nairn on 21/03/2016.
 */
public class OSBotLogger extends MarkerIgnoringBase {

    private final String name;

    @SneakyThrows
    private static Logger getLogger() {
        return StaticLoggerBinder.getSingleton().call();
    }

    OSBotLogger(String name) {
        this.name = name;
    }

    private enum Level {
        TRACE("[TRACE]") { //Could totally be refactored to use the cOM1 methods.

            @Override
            protected void perform(String log) {
                if (getLogger() != null) getLogger().debug(log);
            }

        }, DEBUG("[DEBUG]") {
            @Override
            protected void perform(String log) {
                if (getLogger() != null) getLogger().debug(log);
            }

        }, INFO("[INFO]") {
            @Override
            protected void perform(String log) {
                if (getLogger() != null) getLogger().info(log);
            }

        }, WARN("[WARN]") {
            @Override
            protected void perform(String log) {
                if (getLogger() != null) getLogger().warn(log);
            }

        }, ERROR("[ERROR]") {
            @Override
            protected void perform(String log) {

                if (getLogger() != null) getLogger().error(log);
            }
        };

        private String pre;

        Level(String pre) {
            this.pre = pre;
        }

        protected abstract void perform(String log);

        public void log(OSBotLogger osb, String str, Object... objects) {
            perform(osb.name + ": " + format(str, objects)); //Not proud of this, gross!
        }

        public void log(OSBotLogger osb, String str, Throwable t) {
            getLogger().error(osb.name + " " + format(str), t);
        }

        public String format(String str, Object... objects) {
            if (str == null) {
                return this.pre + " null";
            }
            return this.pre + " " + String.format(str, objects);
        }
    }

    private boolean trace = false;
    private boolean debug = true;
    private boolean info = true;
    private boolean warn = true;
    private boolean error = true;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isTraceEnabled() {
        return this.trace;
    }

    @Override
    public void trace(String msg) {
        Level.TRACE.log(this, msg);
    }

    @Override
    public void trace(String format, Object arg) {
        Level.TRACE.log(this, format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        Level.TRACE.log(this, format, arg1, arg2);

    }

    @Override
    public void trace(String format, Object... arguments) {
        Level.TRACE.log(this, format, arguments);

    }

    @Override
    public void trace(String msg, Throwable t) {
        Level.TRACE.log(this, msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.debug;
    }

    @Override
    public void debug(String msg) {
        Level.DEBUG.log(this, msg);
    }

    @Override
    public void debug(String format, Object arg) {
        Level.DEBUG.log(this, format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        Level.DEBUG.log(this, format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        Level.DEBUG.log(this, format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        Level.DEBUG.log(this, msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return this.info;
    }

    @Override
    public void info(String msg) {
        Level.INFO.log(this, msg);
    }

    @Override
    public void info(String format, Object arg) {
        Level.INFO.log(this, format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        Level.INFO.log(this, format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        Level.INFO.log(this, format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        Level.INFO.log(this, msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return this.warn;
    }

    @Override
    public void warn(String msg) {
        Level.WARN.log(this, msg);
    }

    @Override
    public void warn(String format, Object arg) {
        Level.WARN.log(this, format, arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        Level.WARN.log(this, format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        Level.WARN.log(this, format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t) {
        Level.WARN.log(this, msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return this.error;
    }

    @Override
    public void error(String msg) {
        Level.ERROR.log(this, msg);
    }

    @Override
    public void error(String format, Object arg) {
        Level.ERROR.log(this, format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        Level.ERROR.log(this, format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        Level.ERROR.log(this, format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        Level.ERROR.log(this, msg, t);
    }

}
