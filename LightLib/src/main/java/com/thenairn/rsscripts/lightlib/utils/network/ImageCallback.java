package com.thenairn.rsscripts.lightlib.utils.network;

import okhttp3.Call;
import okhttp3.Response;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * Created by Thomas Nairn on 01/04/2016.
 */
public abstract class ImageCallback extends TypedCallback<Image> {
    @Override
    protected Image convert(Call call, Response response) throws Exception {
        return ImageIO.read(response.body().byteStream());
    }
}
