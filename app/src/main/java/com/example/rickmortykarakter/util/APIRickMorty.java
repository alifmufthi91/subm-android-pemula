package com.example.rickmortykarakter.util;

import android.util.Log;

import com.example.rickmortykarakter.model.Karakter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIRickMorty {

    private static final String TAG = "Error Api";
    private static final String url = "https://rickandmortyapi.com/api/character/";


    public ArrayList<Karakter> getCharacters() {
        OkHttpClient httpClient = new OkHttpClient();

        HttpUrl.Builder httpBuider = HttpUrl.parse(url).newBuilder();

        Request request = new Request.Builder().url(httpBuider.build()).build();

        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            return parseListKarakterFromJSON(response.body().string());
        } catch (IOException e) {
            Log.e(TAG, "error in getting response get request with query string okhttp");
        }
        return null;
    }

    public ArrayList<Karakter> parseListKarakterFromJSON(String response){
        ArrayList<Karakter> karakters = new ArrayList<Karakter>();

        try {
            JSONObject jsonObj = new JSONObject(response);
            JSONArray characterList = jsonObj.getJSONArray("results");
            for(int i = 0; i < characterList.length(); i++){
                JSONObject karakterJSON = characterList.getJSONObject(i);

                karakters.add(new Karakter(
                        karakterJSON.getString("name"),
                        karakterJSON.getString("status"),
                        karakterJSON.getString("species"),
                        karakterJSON.getString("gender"),
                        karakterJSON.getJSONObject("origin").getString("name"),
                        karakterJSON.getString("image"),
                        karakterJSON.getJSONObject("location").getString("name")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return karakters;
    }

}
