package com.cedaniel200.screenplay.restapi.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConverterJson {

    private static Gson gson;

    public static <T> T fromJson(String json, Class<T> classOfT){
        gson = getGson();
        return gson.fromJson(json, classOfT);
    }

    private static Gson getGson() {
        if (gson == null){
            gson = new GsonBuilder().create();
        }
        return gson;
    }

}
