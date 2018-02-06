package com.example.android.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    private EditText mEditText;
    private Button mButton;
    private CurrentWeather mCurrentWeather;
    private ImageView mImageView;
    private TextView mCityText;
    private TextView mCondDescr;
    private TextView mTemp;
    private TextView mHum;
    private TextView mWindSpeed;
    private TextView mWindDegree;
    private TextView mPress;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.searchEdit);

        mButton = findViewById(R.id.searchButton);
        mButton.setOnClickListener(this);

        mImageView = findViewById(R.id.condIcon);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        mPress = findViewById(R.id.press);

        mCityText = findViewById(R.id.cityText);
        mCondDescr = findViewById(R.id.condDescr);
        mTemp = findViewById(R.id.temp);
        mHum = findViewById(R.id.hum);
        mWindSpeed = findViewById(R.id.windSpeed);
        mWindDegree = findViewById(R.id.windDeg);
    }

    private void clearFields(){
        mCityText.setText("");
        mCondDescr.setText("");
        mTemp.setText("");
        mHum.setText("");
        mWindSpeed.setText("");
        mWindDegree.setText("");
        mImageView.setImageBitmap(null);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG,"test");
        if (v.getId()==R.id.searchButton){
            if(mEditText.getText().length()<4){
                mEditText.setText("Enter city name");
                return;
            }
            clearFields();
            RequestWeatherTask task = new RequestWeatherTask(mEditText.getText().toString());
            task.execute();
        }
    }

    private class RequestWeatherTask extends AsyncTask<Void, Void, CurrentWeather> {
        private String query;

        public RequestWeatherTask(String query) {
            this.query = query;
        }

        @Override
        protected CurrentWeather doInBackground(Void... voids) {
            HTTPClientWeather clientWeather = new HTTPClientWeather(query);
            String weatherData = clientWeather.getWeatherData();
            CurrentWeather weather = CurrentWeatherParser.getCurrentWeather(weatherData);
            weather.mIconData = clientWeather.getIconData(weather.mCurrentCondition.getIcon());
            return weather;
        }

        @Override
        protected void onPostExecute(CurrentWeather currentWeather) {
            super.onPostExecute(currentWeather);

            mCurrentWeather = currentWeather;
            mCityText.setText(mCurrentWeather.mLocationSetting.getCity());
            mCondDescr.setText(mCurrentWeather.mCurrentCondition.getDescr());
            mTemp.setText(String.valueOf(mCurrentWeather.mTemperature.getTemp()));
            mHum.setText(String.valueOf(mCurrentWeather.mCurrentCondition.getHumidity()));
            mWindSpeed.setText(String.valueOf(mCurrentWeather.mWind.getSpeed()));
            mWindDegree.setText(String.valueOf(mCurrentWeather.mWind.getDegree()));
            mPress.setText(String.valueOf(mCurrentWeather.mCurrentCondition.getPressure()));

            mImageView.setImageBitmap(BitmapFactory.decodeByteArray(
                    currentWeather.mIconData,0,currentWeather.mIconData.length));
        }
    }


}
