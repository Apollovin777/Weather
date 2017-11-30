package com.example.android.weather;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.text.format.DateFormat;
import com.android.volley.RequestQueue;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Yurko on 19.11.2017.
 */

public class WeatherActivity extends Activity {
    private JSONParseForecast forecast;
    private JSONParseCurrent current;

    public void setForecast(JSONParseForecast forecast) {
        this.forecast = forecast;
    }

    public void setCurrent(JSONParseCurrent current) {
        this.current = current;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");
        RequestQueue queue = SingletonRequestQueue.getInstance(this.getApplicationContext()).
                getRequestQueue();

        JRequest jsObjRequest = new JRequest(cityName,this,false);
        queue.add(jsObjRequest.getRequest());
        JRequest forecastRequest = new JRequest(cityName,this, true);
        queue.add(forecastRequest.getRequest());
    }

    public void UpdateCurrent(){

        TextView currTemp = findViewById(R.id.currentTemp);
        TextView desc = findViewById(R.id.description);
        TextView time = findViewById(R.id.time);
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy  hh:mm a");
        try {
            time.setText(format.format(current.getDate()));
            currTemp.setText(String.valueOf(current.getTemp()));
            desc.setText(current.getDesc());

        }
        catch (JSONException e)
        {
            Log.i("EXCEPTION",e.getMessage());
        }
    }

    public void UpdateForecast() {
        TableLayout table = findViewById(R.id.tableMain);
        TableRow rowTitle = new TableRow(this);
        rowTitle.setBackgroundColor(Color.YELLOW);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView title = new TextView(this);
        title.setText("TEST");
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);



        TableRow dateRow = new TableRow(this);
        dateRow.setBackgroundColor(Color.CYAN);
        TableRow tempRow = new TableRow(this);
        tempRow.setBackgroundColor(Color.BLUE);

        try {
            Date[] dates = forecast.getDatesArray();
            String[] temps = forecast.getTempsArray();

            SimpleDateFormat format = new SimpleDateFormat("dd MM");
            String day;
            Map<String,Integer> numberOfDays = new LinkedHashMap<>();

            for(int i = 0; i < dates.length;i++){
                day = (String) DateFormat.format("dd",   dates[i]);
                if(numberOfDays.containsKey(day))
                    numberOfDays.put(day,(numberOfDays.get(day)+1));
                else
                    numberOfDays.put(day,0);
            }

            for (String s:numberOfDays.keySet()
                 ) {
                TextView textView1 = new TextView(this);
                textView1.setText(s);
                TableRow.LayoutParams params = new TableRow.LayoutParams();
                title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                params.span = numberOfDays.get(s);
                dateRow.addView(textView1,params);
            }

            TableRow.LayoutParams params = new TableRow.LayoutParams();
            params.span = numberOfDays.size();
            rowTitle.addView(title, params);
            table.addView(rowTitle);
            table.addView(dateRow);

            SimpleDateFormat formatTime = new SimpleDateFormat("hh mm");

            for (int i = 0; i < dates.length;i++)
            {
                TextView textView2= new TextView(this);
                Log.i("TIME",formatTime.format(dates[i]));
                textView2.setText(formatTime.format(dates[i]) + "\n" + temps[i]);
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12 );
                tempRow.addView(textView2);
            }

            table.addView(tempRow);
        }
        catch (JSONException e){}



    }


//    public void UpdateForecast(){
//        final TableLayout table = findViewById(R.id.tableMain);
//        TableRow rowTitle = new TableRow(this);
//        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
//
//
//
//        try {
//            Date[] dates = forecast.getDatesArray();
//            String[] temps = forecast.getTempsArray();
//            String[] pressures = forecast.getPressuresArray();
//
//            SimpleDateFormat format = new SimpleDateFormat("dd MM");
//            String day;
//            Map<String,Integer> numberOfDays = new HashMap<>();
//
//            for(int i = 0; i < dates.length;i++){
//                day = (String) DateFormat.format("dd",   dates[0]);
//                if(numberOfDays.containsKey(day))
//                    numberOfDays.put(day,(numberOfDays.get(day)+1));
//                else
//                    numberOfDays.put(day,0);
//            }
//            TableRow.LayoutParams params = new TableRow.LayoutParams();
//            params.span = numberOfDays.size();
//            rowTitle.addView(rowTitle, params);
//            TextView title = new TextView(this);
//            title.setText("Weather Table");
//            rowTitle.addView(title, params);
//
//            table.addView(rowTitle);
//            //setContentView(table);
//
//        }
//        catch (JSONException e){}
//
//    }

}
