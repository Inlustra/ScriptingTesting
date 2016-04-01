package com.thenairn.rsscripts.lightlib.utils.network;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public abstract class TypedCallback<T> implements Callback {

    @Override
    public void onFailure(Call call, IOException e) {
        onFailure(call, (Exception) e);
    }

    public void onFailure(Call call, Exception exception) {
        log.error("Exception for url: " + call.request().toString(), exception);
    }

    @Override
    public void onResponse(Call call, Response response) {
        try {
            onResponse(call, response, convert(call, response));
        } catch (Exception e) {
            onFailure(call, e);
        }
    }

    protected abstract T convert(Call call, Response response) throws Exception;

    public abstract void onResponse(Call call, Response response, T object);

}
