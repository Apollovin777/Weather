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
import com.android.volley.RequestQueue;



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
            
        }
        else
            Log.i("First Run", "APP_PREFERENCES_CITY not exist");
    }

    public void onPressSearch(View view) {

        String cityName = mEditText.getText().toString();
        if (cityName.length() > 2) {
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_CITY,cityName);
            editor.apply();


            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra("cityName", cityName);
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
