package com.example.android.weather;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by UBanit on 1/31/2018.
 */

public class HTTPClientWeather {
    private static final String TAG = "HTTPClientWeather";
    private static final String APPID = "f0fd052a5dd68c962d6cc9aa80735ed4";
    private static final String REQUEST_BY_CITYNAME = "q";
    private static final String METRIC = "metric";
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    private static final Uri ENDPOINT = Uri
            .parse("http://api.openweathermap.org/data/2.5/weather")
            .buildUpon()
            .appendQueryParameter("appid", APPID)
            .appendQueryParameter("units",METRIC)
            .build();
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private String mUrl;


    public HTTPClientWeather(String query) {

        mUrl = ENDPOINT.buildUpon()
                .appendQueryParameter(REQUEST_BY_CITYNAME,query)
                .build()
                .toString();
    }



    public String getWeatherData() {
        String result;
        String inputLine;
        try {
            URL myUrl = new URL(mUrl);

            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();

            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);


            connection.connect();

            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());

            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            reader.close();
            streamReader.close();

            return result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    public byte[] getIconData(String code){
        HttpURLConnection connection = null;
        InputStream is = null;
        try {
            URL iconURL = new URL(IMG_URL + code + ".png");
            //Create a connection
            connection = (HttpURLConnection)
                    iconURL.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.connect();

             is = connection.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream ();

            while (is.read(buffer) != -1){
                byteArrayOutputStream.write(buffer);
            }

            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();

        }
        finally {
            try {is.close();}catch (IOException e){Log.i(TAG,e.getMessage());}
            connection.disconnect();
        }

        return null;

    }

}
