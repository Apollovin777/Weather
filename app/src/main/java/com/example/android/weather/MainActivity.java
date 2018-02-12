package com.example.android.weather;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.example.android.weather.AccuWeather.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    private AutoCompleteTextView mEditText;
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
    private ArrayAdapter<String> mAutoCompleteAdapter;
    private List<String> mListSuggestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListSuggestions = new ArrayList<>();

        mAutoCompleteAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, mListSuggestions);

        mEditText = findViewById(R.id.autoCompleteTextView);
        mEditText.setDropDownBackgroundResource(R.color.colorPrimaryDark);
        mEditText.setThreshold(2);
        mEditText.setAdapter(mAutoCompleteAdapter);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>2){
                    AutoCompleteTask task = new AutoCompleteTask();
                    //task.execute(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

    private class AutoCompleteTask extends AsyncTask<String,Void,List<AutoCompleteSearch>>{


        @Override
        protected List<AutoCompleteSearch> doInBackground(String... strings) {
            return AutoCompleteSearchRequest.getAutoCompleteList(strings[0]);
        }

        @Override
        protected void onPostExecute(List<AutoCompleteSearch> autoCompleteSearch) {
            super.onPostExecute(autoCompleteSearch);

            mListSuggestions.clear();
            if (autoCompleteSearch != null) {
                for (int i = 0; i < autoCompleteSearch.size(); i++) {
                    mListSuggestions.add(autoCompleteSearch.get(i).mLocalizedName);
                }
                if (mListSuggestions.size() > 0) {
                    mAutoCompleteAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_single_choice, mListSuggestions);
                    mAutoCompleteAdapter.notifyDataSetChanged();
                    mEditText.setAdapter(mAutoCompleteAdapter);
                }
            }
        }
    }


}
