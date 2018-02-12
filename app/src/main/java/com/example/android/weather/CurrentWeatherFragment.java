package com.example.android.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CurrentWeatherFragment extends Fragment {
    private static final String ARG_CITY_KEY = "CITY_KEY";

    public static Fragment newInstance(String cityKey) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CITY_KEY, cityKey);
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
