package com.example.android.weather;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Yurko on 18.11.2017.
 */

public class JRequest {
    private JsonObjectRequest mRequest;
    private Response.Listener mResponseListener;
    private Response.ErrorListener mErrorListener;
    private final String APPID = "f0fd052a5dd68c962d6cc9aa80735ed4";
    String urlBaseCurrent = "http://api.openweathermap.org/data/2.5/weather?appid=" + APPID + "&q=";
    String urlBaseForecast = "http://api.openweathermap.org/data/2.5/forecast?appid=" + APPID + "&q=";

    public JRequest(String cityName, final MainActivity activity) {
        String url = urlBaseCurrent + cityName;
        Log.i("URL",url);
        mResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                activity.UpdateField(new JSONParse(response));
            }
        };
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                activity.OnErrorUpdate(error);
            }
        };
        mRequest =  new JsonObjectRequest(Request.Method.GET, url, null, mResponseListener,
                mErrorListener);
    }

    public JRequest(String cityName, final MainActivity activity,boolean forecast) {
        String url = urlBaseForecast + cityName;
        Log.i("URL",url);
        mResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                activity.UpdateField(new JSONParseForecast(response));
            }
        };
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                activity.OnErrorUpdate(error);
            }
        };
        mRequest =  new JsonObjectRequest(Request.Method.GET, url, null, mResponseListener,
                mErrorListener);
    }

    public JsonObjectRequest getRequest() {
        return mRequest;
    }
}
