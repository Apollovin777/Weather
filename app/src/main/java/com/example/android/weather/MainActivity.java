package com.example.android.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;


public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private TextView mTextView;
    private TextView mTextForecast;
    public static final String APP_PREFERENCES = "WeatherSettings";
    public static final String APP_PREFERENCES_CITY = "city";
    private SharedPreferences mSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.inputCity);
        mTextView = findViewById(R.id.output);
        mTextForecast = findViewById(R.id.forecast);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mSettings.contains(APP_PREFERENCES_CITY)){
            RequestQueue queue = Volley.newRequestQueue(this);
            String cityName = mSettings.getString(APP_PREFERENCES_CITY,"");
            JRequest jsObjRequest = new JRequest(cityName,this);
            queue.add(jsObjRequest.getRequest());
        }
        else
            Log.i("First Run", "APP_PREFERENCES_CITY not exist");
    }

    public void onPressSearch(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String cityName = mEditText.getText().toString();
        if (cityName != null && cityName.length() > 2) {
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_CITY,cityName);
            editor.apply();
            JRequest jsObjRequest = new JRequest(cityName,this);
            JRequest forecastRequest = new JRequest(cityName,this, true);
            queue.add(jsObjRequest.getRequest());
            queue.add(forecastRequest.getRequest());
        }
    }

    public void OnErrorUpdate(VolleyError requestError){
            if (requestError instanceof TimeoutError || requestError instanceof NoConnectionError) {
                Log.i("ERRORREQUEST", "TimeoutError");
            } else if (requestError instanceof AuthFailureError) {
                Log.i("ERRORREQUEST", "AuthFailureError");
            } else if (requestError instanceof ServerError) {
                mTextView.setText("Invalid city name");
                Log.i("ERRORREQUEST", "ServerError");
            } else if (requestError instanceof NetworkError) {
                Log.i("ERRORREQUEST", "NetworkError");
            } else if (requestError instanceof ParseError) {
                Log.i("ERRORREQUEST", "ParseError");
            }
    }

    public void UpdateField(JSONParse resultOfRequest){
            StringBuilder builder = new StringBuilder();
            try {
                builder.append("City: " + resultOfRequest.getCityName() + "\n");
                builder.append("Temp: " + resultOfRequest.getTemp() + "\n");
                builder.append("Humidity: " + resultOfRequest.getHumidity() + "\n");
                builder.append("Pressure: " + resultOfRequest.getPressure() + "\n");
                builder.append("Temp_min: " + resultOfRequest.getTempMin() + "\n");
                builder.append("Temp_max: " + resultOfRequest.getTempMax() + "\n");
                mTextView.setText(builder.toString());
            } catch (JSONException e) {
                mTextView.setText(e.getMessage());
            }
    }

    public void UpdateField(JSONParseForecast resultOfRequest){
        StringBuilder builder = new StringBuilder();
        try {
            builder.append("City: " + resultOfRequest.getCityName() + "\n");
            builder.append("ID: " + resultOfRequest.getCityID() + "\n");
            builder.append("Temps:" + resultOfRequest.getTemps());
            mTextForecast.setText(builder.toString());
        } catch (JSONException e) {
            mTextForecast.setText(e.getMessage());
        }
    }
}
