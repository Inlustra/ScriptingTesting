package com.thenairn.rsscripts.lightlib.api;

import com.thenairn.rsscripts.lightlib.Inlustra;

/**
 * Created by thoma on 03/04/2016.
 */
public interface LightProvider {
    ItemAPI getItemAPI();

    NetworkAPI getNetworkAPI();

    ObjectAPI getObjectAPI();

    WorldAPI getWorldAPI();

    Inlustra inlustra();
}
