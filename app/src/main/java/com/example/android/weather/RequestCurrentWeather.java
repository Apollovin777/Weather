package com.example.android.weather;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;



public class RequestCurrentWeather {

    private static final String TAG = "RequestCurrentWeather";
    private static final String APPID = "f0fd052a5dd68c962d6cc9aa80735ed4";
    private static final String REQUEST_BY_CITYNAME = "q";
    private static final String METRIC = "metric";
    private static final Uri ENDPOINT = Uri
            .parse("http://api.openweathermap.org/data/2.5/weather")
            .buildUpon()
            .appendQueryParameter("appid", APPID)
            .appendQueryParameter("units",METRIC)
            .build();

    public Request getRequest() {
        return mRequest;
    }

    private Request mRequest;

    public static String RequestCurrentWeatherURL(String cityName) {
        String url;
        Uri.Builder uriBuilder = ENDPOINT.buildUpon()
                .appendQueryParameter(REQUEST_BY_CITYNAME,cityName);
        url = uriBuilder.build().toString();
        return url;

    }

}
