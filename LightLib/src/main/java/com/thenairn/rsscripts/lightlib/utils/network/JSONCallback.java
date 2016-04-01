package com.thenairn.rsscripts.lightlib.utils.network;

import lombok.Setter;
import okhttp3.Call;
import okhttp3.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by Thomas Nairn on 01/04/2016.
 */
public abstract class JSONCallback extends TypedCallback<JSONObject> {

    @Setter
    private static JSONParser DEFAULT_PARSER = new JSONParser();

    private final JSONParser parser;

    public JSONCallback() {
        this(DEFAULT_PARSER);
    }

    public JSONCallback(JSONParser parser) {
        this.parser = parser;
    }

    @Override
    protected JSONObject convert(Call call, Response response) throws IOException, ParseException {
        return (JSONObject) parser.parse(response.body().string());
    }

}
