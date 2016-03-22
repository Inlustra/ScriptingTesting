package com.thenairn.rsscripts.lightlib.utils.misc;

/**
 * Created by Robin Nairn on 12/03/2016.
 */
public abstract class Cached<T> {
    private T cached;

    public T get() {
        return cached == null ? cached = load() : cached;
    }

    public void invalidate() {
        this.cached = null;
    }

    public void revalidate() {
        this.cached = load();
    }

    protected abstract T load();
}
