package com.example.android.weather.AccuWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UBanit on 2/7/2018.
 */

public class AutoCompleteSearchParser {

    public static List<AutoCompleteSearch> parse(String data){
        List<AutoCompleteSearch> list = new ArrayList<>();

        try {
            JSONArray jArray = new JSONArray(data);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jsonObject = jArray.getJSONObject(i);

                AutoCompleteSearch acSearch = new AutoCompleteSearch();
                acSearch.mVersion = jsonObject.getInt("Version");
                acSearch.mKey = jsonObject.getString("Key");
                acSearch.mType = jsonObject.getString("Type");
                acSearch.mRank = jsonObject.getInt("Rank");
                acSearch.mLocalizedName = jsonObject.getString("LocalizedName");

                JSONObject jsonCountry = jsonObject.getJSONObject("Country");
                acSearch.mCountry.setID(jsonCountry.getString("ID"));
                acSearch.mCountry.setLocalizedName(jsonCountry.getString("LocalizedName"));

                JSONObject jsonAdmArea = jsonObject.getJSONObject("AdministrativeArea");
                acSearch.mArea.setID(jsonAdmArea.getString("ID"));
                acSearch.mArea.setLocalizedName(jsonAdmArea.getString("LocalizedName"));

                list.add(acSearch);
            }

        }
        catch (JSONException e){
            return list;
        }

        return list;
    }


}
