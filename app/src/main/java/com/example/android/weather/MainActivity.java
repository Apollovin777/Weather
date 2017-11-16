package com.example.android.weather;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.inputCity);
        mTextView = (TextView) findViewById(R.id.output);
    }

    public void onPressSearch(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.openweathermap .org/data/2.5/weather?q=";
        String appid = "f0fd052a5dd68c962d6cc9aa80735ed4";
        String userInput = mEditText.getText().toString();
        if (userInput != null && userInput.length() > 2) {
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
                Log.i("Error request", "TimeoutError");
            } else if (requestError instanceof AuthFailureError) {
                Log.i("Error request", "AuthFailureError");
            } else if (requestError instanceof ServerError) {
                Log.i("Error request", "ServerError");
            } else if (requestError instanceof NetworkError) {
                Log.i("Error request", "NetworkError");
            } else if (requestError instanceof ParseError) {
                Log.i("Error request", "ParseError");
            }

    }

    private void UpdateField(){

            StringBuilder builder = new StringBuilder();
            try {
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
