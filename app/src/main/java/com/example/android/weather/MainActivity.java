package com.example.android.weather;

import android.content.Context;
import android.content.Intent;
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
       // mTextView = findViewById(R.id.output);
       // mTextForecast = findViewById(R.id.forecast);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mSettings.contains(APP_PREFERENCES_CITY)){
            RequestQueue queue = SingletonRequestQueue.getInstance(this.getApplicationContext()).
                    getRequestQueue();
            String cityName = mSettings.getString(APP_PREFERENCES_CITY,"");
            JRequest jsObjRequest = new JRequest(cityName,this,false);
            queue.add(jsObjRequest.getRequest());
        }
        else
            Log.i("First Run", "APP_PREFERENCES_CITY not exist");
    }

    public void onPressSearch(View view) {
        RequestQueue queue = SingletonRequestQueue.getInstance(this.getApplicationContext()).
                getRequestQueue();
        String cityName = mEditText.getText().toString();
        if (cityName != null && cityName.length() > 2) {
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_CITY,cityName);
            editor.apply();
            JRequest jsObjRequest = new JRequest(cityName,this,false);
            JRequest forecastRequest = new JRequest(cityName,this, true);
            queue.add(jsObjRequest.getRequest());
            queue.add(forecastRequest.getRequest());
            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            startActivity(intent);
        }
    }



    public void UpdateField(JSONParseForecast resultOfRequest){
//        StringBuilder builder = new StringBuilder();
//        try {
//            builder.append("City: " + resultOfRequest.getCityName() + "\n");
//            builder.append("ID: " + resultOfRequest.getCityID() + "\n");
//            builder.append("Temps:" + resultOfRequest.getTemps());
//            mTextForecast.setText(builder.toString());
//        } catch (JSONException e) {
//            mTextForecast.setText(e.getMessage());
//        }
    }

}
