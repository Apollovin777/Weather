package com.example.android.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private TextView mTextView;
    private JSONParse resultOfRequest;
    private VolleyError requestError;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_CITY = "city";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.inputCity);
        mTextView = findViewById(R.id.output);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mSettings.contains(APP_PREFERENCES_CITY)){
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://api.openweathermap.org/data/2.5/weather?q=";
            String appid = "f0fd052a5dd68c962d6cc9aa80735ed4";
            String userInput = mSettings.getString(APP_PREFERENCES_CITY,"");
            String urlForRequest = url + userInput + "&appid=" + appid;
            Log.i("REQUESTURL",urlForRequest);
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, urlForRequest, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            resultOfRequest = new JSONParse(response);
                            UpdateField();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            requestError = error;
                            OnErrorUpdate();
                        }
                    });
            queue.add(jsObjRequest);
        }
        else
            Log.i("First Run", "APP_PREFERENCES_CITY not exist");
    }

    public void onPressSearch(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=";
        String appid = "f0fd052a5dd68c962d6cc9aa80735ed4";
        String userInput = mEditText.getText().toString();
        if (userInput != null && userInput.length() > 2) {
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_CITY,userInput);
            editor.apply();
            String urlForRequest = url + userInput + "&appid=" + appid;
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, urlForRequest, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            resultOfRequest = new JSONParse(response);
                            UpdateField();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            requestError = error;
                            OnErrorUpdate();
                        }
                    });
            queue.add(jsObjRequest);
        }


    }
    private void OnErrorUpdate(){

            if (requestError instanceof TimeoutError || requestError instanceof NoConnectionError) {
                Log.i("ERRORREQUEST", "TimeoutError");
            } else if (requestError instanceof AuthFailureError) {
                Log.i("ERRORREQUEST", "AuthFailureError");
            } else if (requestError instanceof ServerError) {
                Log.i("ERRORREQUEST", "ServerError");
            } else if (requestError instanceof NetworkError) {
                Log.i("ERRORREQUEST", "NetworkError");
            } else if (requestError instanceof ParseError) {
                Log.i("ERRORREQUEST", "ParseError");
            }

    }

    private void UpdateField(){

            StringBuilder builder = new StringBuilder();
            try {
                builder.append("City: " + resultOfRequest.getCityName() + "\n");
                builder.append("Temp: " + resultOfRequest.getTemp() + "\n");
                builder.append("Humidity: " + resultOfRequest.getHumidity() + "\n");
                builder.append("Pressure: " + resultOfRequest.getPressure() + "\n");
                builder.append("Temp_min: " + resultOfRequest.getTempMin() + "\n");
                builder.append("Temp_max: " + resultOfRequest.getTempMax() + "\n");
                mTextView.setText(builder.toString());
            } catch (JSONException e) {
                mTextView.setText(e.getMessage());
            }


    }
}
