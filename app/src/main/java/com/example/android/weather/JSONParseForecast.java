package com.example.android.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yurko on 18.11.2017.
 */

public class JSONParseForecast extends JSONParse{

    public JSONParseForecast(JSONObject jsonObject) {
        super(jsonObject);
    }

    public String getCityName() throws  JSONException{
        return mJsonObj.getJSONObject("city").getString("name");
    }

    public String getCityID() throws  JSONException{
        return mJsonObj.getJSONObject("city").getString("id");
    }

    public String getTemps() throws JSONException{
        StringBuilder builder = new StringBuilder();
        double temp;
        JSONArray array = mJsonObj.getJSONArray("list");
        for(int i = 0; i<array.length();i++){
            temp = array.getJSONObject(i).getJSONObject("main").getDouble("temp")-273;
             builder.append(round(temp,0)+"\n");
        }
        return builder.toString();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
