package com.example.android.weather.AccuWeather;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AutoCompleteSearchRequest {
    private static final String TAG = "SEARCHREQUEST_AUTO";
    private static final String RESOURCE_URL = "http://dataservice.accuweather.com/locations/v1/cities/autocomplete";
    private static final String API_KEY = "apikey";
    private static final String LANGUAGE = "language";
    private static final String QUERY = "q";
    private static final String KEY = "I2PFnL5mFw3mQSehUV02mIZ7Y0AzG1Vm";
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    private static final Uri ENDPOINT = Uri
            .parse(RESOURCE_URL)
            .buildUpon()
            .appendQueryParameter(API_KEY, KEY)
            .appendQueryParameter(LANGUAGE,"en-us")
            .build();

    public static List<AutoCompleteSearch> getAutoCompleteList(String s) {

        String inputLine;
        try {
            Uri.Builder uriBuilder = ENDPOINT.buildUpon()
                    .appendQueryParameter(QUERY,s);

            URL myUrl = new URL(uriBuilder.build().toString());

            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();

            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

           // connection.connect();


            Log.i(TAG,String.valueOf(connection.getResponseCode()));
            Log.i(TAG,String.valueOf(connection.getResponseMessage()));

            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());

            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            reader.close();
            streamReader.close();

//          return AutoCompleteSearchParser.parse(stringBuilder.toString());

            s = "[{\"Version\":1,\"Key\":\"326175\",\"Type\":\"City\",\"Rank\":31,\"LocalizedName\":\"Vinnytsia\",\"Country\":{\"ID\":\"UA\",\"LocalizedName\":\"Ukraine\"},\"AdministrativeArea\":{\"ID\":\"05\",\"LocalizedName\":\"Vinnytsya\"}},{\"Version\":1,\"Key\":\"56790\",\"Type\":\"City\",\"Rank\":35,\"LocalizedName\":\"Vina del Mar\",\"Country\":{\"ID\":\"CL\",\"LocalizedName\":\"Chile\"},\"AdministrativeArea\":{\"ID\":\"VS\",\"LocalizedName\":\"Valparaíso\"}},{\"Version\":1,\"Key\":\"355417\",\"Type\":\"City\",\"Rank\":41,\"LocalizedName\":\"Vinh\",\"Country\":{\"ID\":\"VN\",\"LocalizedName\":\"Vietnam\"},\"AdministrativeArea\":{\"ID\":\"22\",\"LocalizedName\":\"Nghệ An\"}},{\"Version\":1,\"Key\":\"356354\",\"Type\":\"City\",\"Rank\":41,\"LocalizedName\":\"Vinh Long\",\"Country\":{\"ID\":\"VN\",\"LocalizedName\":\"Vietnam\"},\"AdministrativeArea\":{\"ID\":\"49\",\"LocalizedName\":\"Vĩnh Long\"}},{\"Version\":1,\"Key\":\"324510\",\"Type\":\"City\",\"Rank\":45,\"LocalizedName\":\"Vinniki\",\"Country\":{\"ID\":\"UA\",\"LocalizedName\":\"Ukraine\"},\"AdministrativeArea\":{\"ID\":\"46\",\"LocalizedName\":\"L'viv\"}},{\"Version\":1,\"Key\":\"356357\",\"Type\":\"City\",\"Rank\":51,\"LocalizedName\":\"Vinh Yen\",\"Country\":{\"ID\":\"VN\",\"LocalizedName\":\"Vietnam\"},\"AdministrativeArea\":{\"ID\":\"70\",\"LocalizedName\":\"Vĩnh Phúc\"}},{\"Version\":1,\"Key\":\"133758\",\"Type\":\"City\",\"Rank\":53,\"LocalizedName\":\"Vincennes\",\"Country\":{\"ID\":\"FR\",\"LocalizedName\":\"France\"},\"AdministrativeArea\":{\"ID\":\"94\",\"LocalizedName\":\"Val-de-Marne\"}},{\"Version\":1,\"Key\":\"41740\",\"Type\":\"City\",\"Rank\":55,\"LocalizedName\":\"Vinhedo\",\"Country\":{\"ID\":\"BR\",\"LocalizedName\":\"Brazil\"},\"AdministrativeArea\":{\"ID\":\"SP\",\"LocalizedName\":\"São Paulo\"}},{\"Version\":1,\"Key\":\"2200791\",\"Type\":\"City\",\"Rank\":55,\"LocalizedName\":\"Vineyard\",\"Country\":{\"ID\":\"US\",\"LocalizedName\":\"United States\"},\"AdministrativeArea\":{\"ID\":\"CA\",\"LocalizedName\":\"California\"}},{\"Version\":1,\"Key\":\"334488\",\"Type\":\"City\",\"Rank\":55,\"LocalizedName\":\"Vineland\",\"Country\":{\"ID\":\"US\",\"LocalizedName\":\"United States\"},\"AdministrativeArea\":{\"ID\":\"NJ\",\"LocalizedName\":\"New Jersey\"}}]";
            return AutoCompleteSearchParser.parse(s);



        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
