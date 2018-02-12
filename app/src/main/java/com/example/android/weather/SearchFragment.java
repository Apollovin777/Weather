package com.example.android.weather;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.weather.AccuWeather.AutoCompleteSearchRequest;
import com.example.android.weather.AccuWeather.AutoCompleteSearch;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private AutoCompleteTextView mEditText;
    private Button mButton;
    private RecyclerView mRecyclerView;
    private List<AutoCompleteSearch> mList = new ArrayList<>();
    private SearchAdapter mAdapter;

    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment,container,false);
        mEditText = view.findViewById(R.id.search_TextView);
        mButton = view.findViewById(R.id.search_Button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTask task = new AutoCompleteTask();
                task.execute(mEditText.getText().toString());
            }
        });

        mRecyclerView =  view.findViewById(R.id.search_RecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private class SearchHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mCityTextView;
        private TextView mLocationTextView;

        public SearchHolder(LayoutInflater inflater, ViewGroup group){
            super(inflater.inflate(R.layout.list_search_city,group,false));
            mCityTextView = itemView.findViewById(R.id.city_name);
            mLocationTextView = itemView.findViewById(R.id.city_location);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            WeatherPreferences prefs = new WeatherPreferences(getActivity());
            prefs.addValue(mList.get(position).mKey);
            Intent i = CurrentWeatherPagerActivity.newIntent(getActivity(),mList.get(position).mKey);
            startActivity(i);
        }
    }

    private class SearchAdapter extends RecyclerView.Adapter<SearchHolder>{
        private List<AutoCompleteSearch> mCities;

        public SearchAdapter(List<AutoCompleteSearch> cities) {
            mCities = cities;
        }

        @Override
        public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SearchHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(SearchHolder holder, int position) {
            holder.mCityTextView.setText(mCities.get(position).mLocalizedName);
            String location = mCities.get(position).mArea.getLocalizedName();
            location += " " +mCities.get(position).mCountry.getLocalizedName();
            holder.mLocationTextView.setText(location);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public void setCities(List<AutoCompleteSearch> list) {
            mCities = list;
        }
    }

    private class AutoCompleteTask extends AsyncTask<String,Void,List<AutoCompleteSearch>> {


        @Override
        protected List<AutoCompleteSearch> doInBackground(String... strings) {
            return AutoCompleteSearchRequest.getAutoCompleteList(strings[0]);
        }

        @Override
        protected void onPostExecute(List<AutoCompleteSearch> autoCompleteSearch) {
            super.onPostExecute(autoCompleteSearch);

            mList.clear();
            if (autoCompleteSearch != null) {
                for (int i = 0; i < autoCompleteSearch.size(); i++) {
                    mList.add(autoCompleteSearch.get(i));
                }

                if (mList.size() > 0) {
                    if (mAdapter == null){
                        mAdapter = new SearchAdapter(autoCompleteSearch);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    else{
                        mAdapter.setCities(mList);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
