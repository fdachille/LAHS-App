package com.losaltosinfo.justin.losinfo;

import org.json.JSONObject;

/**
 * Created by justin on 5/4/2016.
 */
interface AsyncResult
{
    void onResult(JSONObject object);
}