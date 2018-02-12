package com.example.android.weather;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CurrentWeatherPagerActivity extends AppCompatActivity {
    private static final String EXTRA_CITY_KEY =
            "com.example.android.weather.city_key";
    private String[] mCityKeys;
    private ViewPager mViewPager;

    public static Intent newIntent(Context context, String cityKey) {
        Intent intent = new Intent(context,CurrentWeatherPagerActivity.class);
        intent.putExtra(EXTRA_CITY_KEY,cityKey);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.curr_weather_pager);

        WeatherPreferences prefs = new WeatherPreferences(this);
        mCityKeys = prefs.loadArray();
        String cityKey = (String) getIntent().getSerializableExtra(EXTRA_CITY_KEY);

        mViewPager = (ViewPager) findViewById(R.id.curr_weather_view_pager_id);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return CurrentWeatherFragment.newInstance(mCityKeys[position]);
            }

            @Override
            public int getCount() {
                return mCityKeys.length;
            }
        });

        for (int i = 0; i < mCityKeys.length; i++) {
            if (mCityKeys[i].equals(cityKey)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
