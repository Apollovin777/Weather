package com.example.android.weather;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;


public class WeatherPreferences {
    private Context mContext;
    private static final String mArrayName = "WeatherPreferences";

    public WeatherPreferences(Context context) {
        mContext = context;
    }

    public void addValue(String value){
        String[] array = loadArray();
        if(array.length==0){
            String[] first = new String[]{value};
            saveArray(first);
            return;
        }
        else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return;
                }
            }
            int length = array.length + 1;
            String[] newArray = new String[length];
            newArray = Arrays.copyOf(array,length-1);
            newArray[length] = value;
            saveArray(newArray);
        }
    }

    public void saveArray(String[] array) {
        SharedPreferences prefs = mContext.getSharedPreferences(mArrayName, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(mArrayName +"_size", array.length);
        for(int i=0;i<array.length;i++)
            editor.putString(mArrayName + "_" + i, String.valueOf(array[i]));
        editor.commit();
    }

    public String[] loadArray() {
        SharedPreferences prefs = mContext.getSharedPreferences(mArrayName, 0);
        if (prefs == null){
            return new String[0];
        }
        int size = prefs.getInt(mArrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(mArrayName + "_" + i, null);
        return array;
    }
}
