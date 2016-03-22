package com.thenairn.rsscripts.lightlib.utils.misc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Robin Nairn on 12/03/2016.
 */
@Slf4j
public class SimpleTimer {

    private final long start;

    public SimpleTimer() {
        start = System.currentTimeMillis();
    }

    public long elapsed() {
        return System.currentTimeMillis() - start;
    }

    public String getFormatted(String format) {
        return DurationFormatUtils.formatDuration(elapsed(), format);
    }

    public void print() {
        final long hr = TimeUnit.MILLISECONDS.toHours(elapsed());
        final long min = TimeUnit.MILLISECONDS.toMinutes(elapsed() - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(elapsed() - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        final long ms = TimeUnit.MILLISECONDS.toMillis(elapsed() - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
        log.debug(String.format("Time elapsed: %02d:%02d:%02d.%03d", hr, min, sec, ms));
    }
}
