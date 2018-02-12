package com.example.android.weather.AccuWeather;

import com.example.android.weather.CurrentWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.android.weather.AccuWeather.CurrentConditions;



public class CurrentConditionsParser {

    public static CurrentConditions parse(String data){
        CurrentConditions conditions = new CurrentConditions();

        try {
            JSONArray jArray = new JSONArray(data);
            JSONObject jObj = jArray.getJSONObject(0);
            conditions.setLocalObservationDateTime(
                    parceDateTime(getString("LocalObservationDateTime",jObj)));
            conditions.setEpochTime(getLong("EpochTime",jObj));
            conditions.setWeatherText(getString("WeatherText",jObj));
            conditions.setWeatherIcon(getInt("WeatherIcon",jObj));
            conditions.setDayTime(getBoolean("IsDayTime",jObj));
            conditions.setCurrentTemperature(parseUnitsValues( jObj.getJSONObject("Temperature")));
            conditions.setRealFeelTemperature(parseUnitsValues(jObj.getJSONObject("RealFeelTemperature")));
            conditions.setRealFeelTemperature(parseUnitsValues(jObj.getJSONObject("RealFeelTemperatureShade")));
            conditions.setRelativeHumidity(getInt("RelativeHumidity",jObj));
            conditions.setDewPoint(parseUnitsValues(jObj.getJSONObject("DewPoint")));

            //Wind
            conditions.setWind(parseWind(jObj.getJSONObject("Wind")));
            //WindGust
            //conditions.setWindGust(parseSpeed(jObj.getJSONObject("WindGust")));

            conditions.setUVIndex(getInt("UVIndex",jObj));
            conditions.setUVIndexText(getString("UVIndexText",jObj));
            JSONObject jVisibility = jObj.getJSONObject("Visibility");
            CurrentConditions.Visibility visibility =
                    new CurrentConditions.Visibility();
            visibility.setMetric(parseUnits(jVisibility.getJSONObject("Metric")));
            visibility.setImperial(parseUnits(jVisibility.getJSONObject("Imperial")));
            conditions.setVisibility(visibility);

            conditions.setObstructionsToVisibility(getString("ObstructionsToVisibility",jObj));
            conditions.setCloudCover(getInt("CloudCover",jObj));

            JSONObject jCeiling = jObj.getJSONObject("Ceiling");
            CurrentConditions.Ceiling ceiling =
                    new CurrentConditions.Ceiling();
            ceiling.setMetric(parseUnits(jCeiling.getJSONObject("Metric")));
            ceiling.setImperial(parseUnits(jCeiling.getJSONObject("Imperial")));
            conditions.setmCeiling(ceiling);

            JSONObject jPressure = jObj.getJSONObject("Pressure");
            CurrentConditions.Pressure pressure =
                    new CurrentConditions.Pressure();
            pressure.setMetric(parseUnits(jPressure.getJSONObject("Metric")));
            pressure.setImperial(parseUnits(jPressure.getJSONObject("Imperial")));
            conditions.setmPressure(pressure);

            JSONObject jPressureTendency = jObj.getJSONObject("PressureTendency");
            CurrentConditions.PressureTendency pressureTendency =
                    new CurrentConditions.PressureTendency();
            pressureTendency.setLocalizedText(getString("LocalizedText",jPressureTendency));
            pressureTendency.setCode(getString("Code",jPressureTendency));
            conditions.setmPressureTendency(pressureTendency);
        }
        catch (JSONException e){
            return null;
        }

        return conditions;
    }

    private static CurrentConditions.Wind parseWind(JSONObject jObj) throws JSONException{
        CurrentConditions.Wind wind = new CurrentConditions.Wind();
        CurrentConditions.WindDirection direction = new CurrentConditions.WindDirection();

        CurrentConditions.Speed speed = new CurrentConditions.Speed();
        CurrentConditions.MeagumentUnits metric = new CurrentConditions.MeagumentUnits();
        CurrentConditions.MeagumentUnits imperial = new CurrentConditions.MeagumentUnits();

        JSONObject objDirection = jObj.getJSONObject("Direction");
        direction.setDegrees(getInt("Degrees",objDirection));
        direction.setLocalized(getString("Localized",objDirection));
        direction.setEnglish(getString("English",objDirection));
        wind.setDirection(direction);

        wind.setSpeed(parseSpeed(jObj.getJSONObject("Speed")));
        return wind;
    }

    private static CurrentConditions.Speed parseSpeed(JSONObject jObj)throws JSONException{
        CurrentConditions.Speed speed = new CurrentConditions.Speed();
        speed.setMetric(parseUnits(jObj.getJSONObject("Metric")));
        speed.setImperial(parseUnits(jObj.getJSONObject("Imperial")));

        return speed;
    }

    private static CurrentConditions.MeagumentUnits parseUnits(JSONObject jObj) throws JSONException{
        CurrentConditions.MeagumentUnits unit = new CurrentConditions.MeagumentUnits();
        unit.setValue(getDouble("Value",jObj));
        unit.setUnit(getString("Unit",jObj));
        unit.setUnitType(getInt("UnitType",jObj));
        return unit;
    }

    private static CurrentConditions.Temperature parseUnitsValues(JSONObject jObj) throws JSONException{
        CurrentConditions.Temperature temperature = new CurrentConditions.Temperature();

        JSONObject jObjMetric = jObj.getJSONObject("Metric");
        temperature.setMetric(parseUnits(jObjMetric));

        JSONObject jObjImperial = jObj.getJSONObject("Imperial");
        temperature.setImperial(parseUnits(jObjImperial));

        return temperature;
    }

    private static boolean getBoolean(String tag, JSONObject obj) throws JSONException{
        return obj.getBoolean(tag);
    }

    private static int getInt(String tag, JSONObject obj) throws JSONException{
        return obj.getInt(tag);
    }

    private static String getString(String tag, JSONObject obj) throws JSONException{
        return obj.getString(tag);
    }

    private static long getLong(String tag, JSONObject obj) throws JSONException{
        return obj.getLong(tag);
    }

    private static double getDouble(String tag, JSONObject obj) throws JSONException{
        return obj.getDouble(tag);
    }


    private static Date parceDateTime(String value){
        //2018-02-12T14:45:00+02:00

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            Date date = dateFormat.parse(value);
            return date;
        }
        catch (ParseException e){
            return null;
        }
    }
}
