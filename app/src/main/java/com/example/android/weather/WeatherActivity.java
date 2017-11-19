package com.example.android.weather;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

/**
 * Created by Yurko on 19.11.2017.
 */

public class WeatherActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
    }

    public void OnErrorUpdate(VolleyError requestError){
//        if (requestError instanceof TimeoutError || requestError instanceof NoConnectionError) {
//            Log.i("ERRORREQUEST", "TimeoutError");
//        } else if (requestError instanceof AuthFailureError) {
//            Log.i("ERRORREQUEST", "AuthFailureError");
//        } else if (requestError instanceof ServerError) {
//            mTextView.setText("Invalid city name");
//            Log.i("ERRORREQUEST", "ServerError");
//        } else if (requestError instanceof NetworkError) {
//            Log.i("ERRORREQUEST", "NetworkError");
//        } else if (requestError instanceof ParseError) {
//            Log.i("ERRORREQUEST", "ParseError");
//        }
    }

    public void UpdateField(JSONParseCurrent resultOfRequest){
//            StringBuilder builder = new StringBuilder();
//            try {
//                builder.append("City: " + resultOfRequest.getCityName() + "\n");
//                builder.append("Temp: " + resultOfRequest.getTemp() + "\n");
//                builder.append("Humidity: " + resultOfRequest.getHumidity() + "\n");
//                builder.append("Pressure: " + resultOfRequest.getPressure() + "\n");
//                builder.append("Temp_min: " + resultOfRequest.getTempMin() + "\n");
//                builder.append("Temp_max: " + resultOfRequest.getTempMax() + "\n");
//                mTextView.setText(builder.toString());
//            } catch (JSONException e) {
//                mTextView.setText(e.getMessage());
//            }
    }
}
