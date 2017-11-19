package com.example.android.weather;

import org.json.JSONObject;

public class JSONParse {
    protected JSONObject mJsonObj;

    public JSONParse(JSONObject jsonObject) {
        this.mJsonObj = jsonObject;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
