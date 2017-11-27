package com.example.android.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONException;

import java.util.Date;

/**
 * Created by Yurko on 19.11.2017.
 */

public class WeatherActivity extends Activity {
    private JSONParseForecast forecast;
    private JSONParseCurrent current;

    public void setForecast(JSONParseForecast forecast) {
        this.forecast = forecast;
    }

    public void setCurrent(JSONParseCurrent current) {
        this.current = current;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");
        RequestQueue queue = SingletonRequestQueue.getInstance(this.getApplicationContext()).
                getRequestQueue();

        JRequest jsObjRequest = new JRequest(cityName,this,false);
        queue.add(jsObjRequest.getRequest());
        JRequest forecastRequest = new JRequest(cityName,this, true);
        queue.add(forecastRequest.getRequest());
    }

    public void UpdateCurrent(){

        TextView currTemp = findViewById(R.id.currentTemp);
        TextView desc = findViewById(R.id.description);
        TextView time = findViewById(R.id.time);

        try {
            currTemp.setText(String.valueOf(current.getTemp()));
            desc.setText(current.getDesc());
            time.setText(current.getDate().toString());
        }
        catch (JSONException e)
        {
            Log.i("EXCEPTION",e.getMessage());
        }
    }

    public void UpdateForecast(){
        final LinearLayout ll = findViewById(R.id.forecast);
        try {
            Date[] dates = forecast.getDatesArray();
            String[] temps = forecast.getTempsArray();
            String[] pressures = forecast.getPressuresArray();


            for (int i = 0; i < 10; i++) {
                TextView forecastItem = new TextView(this);
                forecastItem.setText(dates[i] + "\n" + temps[i] + "\n" + pressures[i]);
                ll.addView(forecastItem);
            }
        }
        catch (JSONException e){}

    }

}
