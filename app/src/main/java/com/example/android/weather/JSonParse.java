package com.example.android.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ubanit on 11/16/2017.
 */

public class JSONParse {
    private String jsonString;
    private JSONObject jsonObject;

    public JSONParse(String JSONString) throws JSONException{
        this.jsonString = JSONString;
        jsonObject = new JSONObject(jsonString);
    }

    public JSONParse(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public double getTemp() throws JSONException{
        double result = jsonObject.getJSONObject("main").getDouble("temp")-273;
        return round(result,1);
    }

    public int getPressure() throws JSONException{
        return jsonObject.getJSONObject("main").getInt("pressure");
    }

    public int getHumidity() throws JSONException{
        return jsonObject.getJSONObject("main").getInt("humidity");
    }

    public double getTempMin() throws JSONException{
        double result = jsonObject.getJSONObject("main").getInt("temp_min")-273;
        return round(result,0);
    }

    public double getTempMax() throws JSONException{
        double result = jsonObject.getJSONObject("main").getInt("temp_max")-273;
        return round(result,0);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public String getCityName() throws  JSONException{
        return jsonObject.getString("name");
    }


}
