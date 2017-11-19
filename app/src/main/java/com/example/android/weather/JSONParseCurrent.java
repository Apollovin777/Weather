package com.example.android.weather;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParseCurrent extends JSONParse{

    public JSONParseCurrent(JSONObject jsonObject) {
        super(jsonObject);
    }

    public double getTemp() throws JSONException {
        double result = mJsonObj.getJSONObject("main").getDouble("temp")-273;
        return round(result,1);
    }

    public int getPressure() throws JSONException{
        return mJsonObj.getJSONObject("main").getInt("pressure");
    }

    public int getHumidity() throws JSONException{
        return mJsonObj.getJSONObject("main").getInt("humidity");
    }

    public double getTempMin() throws JSONException{
        double result = mJsonObj.getJSONObject("main").getInt("temp_min")-273;
        return round(result,0);
    }

    public double getTempMax() throws JSONException{
        double result = mJsonObj.getJSONObject("main").getInt("temp_max")-273;
        return round(result,0);
    }

    public String getCityName() throws  JSONException{
        return mJsonObj.getString("name");
    }
}
