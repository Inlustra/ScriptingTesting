package com.thenairn.rsscripts.lightlib.api;

import lombok.experimental.Delegate;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Thomas Nairn on 01/04/2016.
 */
public class NetworkAPI extends LightAPI {

    @Delegate
    private OkHttpClient client;

    @Override
    public void initializeModule() {
        client = new OkHttpClient();
    }

    public void request(Request request, Callback callback) {
        client.newCall(request).enqueue(callback);
    }

}
