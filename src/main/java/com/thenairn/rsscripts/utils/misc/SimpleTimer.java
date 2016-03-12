package com.thenairn.rsscripts.utils.misc;

import java.util.concurrent.TimeUnit;

/**
 * Created by Robin Nairn on 12/03/2016.
 */
public class SimpleTimer {

    private static volatile int count = 0;

    private long start;

    public SimpleTimer() {
        start = System.currentTimeMillis();
        System.out.println("Timer #"+count+", Started!");
    }

    public long elapsed() {
        return System.currentTimeMillis() - start;
    }

    public void printFormatted(String format) {
        System.out.printf("Timer #"+count+", "+format, elapsed());
    }

    public void print() {
        final long hr = TimeUnit.MILLISECONDS.toHours(elapsed());
        final long min = TimeUnit.MILLISECONDS.toMinutes(elapsed() - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(elapsed() - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        final long ms = TimeUnit.MILLISECONDS.toMillis(elapsed() - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
        System.out.println(String.format("Timer #"+count+", Time elapsed: %02d:%02d:%02d.%03d", hr, min, sec, ms));
    }
}
