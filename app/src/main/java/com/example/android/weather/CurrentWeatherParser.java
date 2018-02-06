package com.example.android.weather;

import android.location.Location;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by UBanit on 1/30/2018.
 */

public class CurrentWeatherParser {
    private static final String TAG = "CurrentWeatherParser";

    public static CurrentWeather getCurrentWeather(String data){
        try {
            JSONObject jObj = new JSONObject(data);

            CurrentWeather weather = new CurrentWeather();

            LocationSetting loc = new LocationSetting();
            JSONObject coordObj = getObject("coord", jObj);
            loc.setLatitude(getFloat("lat", coordObj));
            loc.setLongitude(getFloat("lon", coordObj));

            JSONObject sysObj = getObject("sys", jObj);
            loc.setCountry(getString("country", sysObj));
            loc.setSunrise(getInt("sunrise", sysObj));
            loc.setSunset(getInt("sunset", sysObj));
            loc.setCity(getString("name", jObj));
            weather.mLocationSetting = loc;

            JSONArray jArr = jObj.getJSONArray("weather");
            JSONObject JSONWeather = jArr.getJSONObject(0);
            weather.mCurrentCondition.setWeatherId(getInt("id", JSONWeather));
            weather.mCurrentCondition.setDescr(getString("description", JSONWeather));
            weather.mCurrentCondition.setCondition(getString("main", JSONWeather));
            weather.mCurrentCondition.setIcon(getString("icon", JSONWeather));

            JSONObject mainObj = getObject("main", jObj);
            weather.mCurrentCondition.setHumidity(getInt("humidity", mainObj));
            weather.mCurrentCondition.setPressure(getInt("pressure", mainObj));
            weather.mTemperature.setMaxTemp(getFloat("temp_max", mainObj));
            weather.mTemperature.setMinTemp(getFloat("temp_min", mainObj));
            weather.mTemperature.setTemp(getFloat("temp", mainObj));

            if (jObj.has("wind")) {
                JSONObject wObj = getObject("wind", jObj);
                weather.mWind.setSpeed(getFloat("speed", wObj));
                weather.mWind.setDegree(getFloat("deg", wObj));
            }else {
                weather.mWind = null;
            }

            if (jObj.has("snow")) {
                JSONObject sObj = getObject("snow", jObj);
                weather.mSnow.setAmmount(getInt("3h", sObj));
            } else{
                weather.mSnow = null;
            }

            if (jObj.has("rain")) {
                JSONObject rObj = getObject("rain", jObj);
                weather.mRain.setAmmount(getInt("3h", rObj));
            } else{
                weather.mRain = null;
            }

            if (jObj.has("clouds")) {
                JSONObject cObj = getObject("clouds", jObj);
                weather.mCouds.setPerc(getInt("all", cObj));
            } else{
                weather.mCouds = null;
            }

            return weather;

        } catch (JSONException e){
            Log.i(TAG,e.getMessage());
            return null;
        }

    }

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
}
