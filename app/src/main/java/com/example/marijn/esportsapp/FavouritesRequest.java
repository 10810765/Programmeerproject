package com.example.marijn.esportsapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FavouritesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private FavouritesRequest.Callback activity;

    // Notify the activity that instantiated the request through callback
    public interface Callback {
        void gotFavourite(ArrayList<FavouritesInformation> streams);
        void gotFavouriteError(String message);
    }

    public FavouritesRequest(Context context) {
        this.context = context;
    }

    // Method will attempt to retrieve the categories from the API
    public void getFavourite(FavouritesRequest.Callback activity, String streamerNames) {
        this.activity = activity;

        // Create a new request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create a JSON object request and add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(" https://api.twitch.tv/kraken/streams?channel=" + streamerNames + "&client_id=43kgmi902ijvh15g5t0m3kxsjckjfn"
                ,null
                , this
                , this);
        queue.add(jsonObjectRequest);
    }

    @Override // Handle on API error response
    public void onErrorResponse(VolleyError error) {
        activity.gotFavouriteError(error.getMessage());
        Log.d("gotFavouritesError", error.getMessage());
    }

    @Override // Handle on API response
    public void onResponse(JSONObject response) {

        try {

            // Instantiate array list
            ArrayList<FavouritesInformation> favouriteArrayList = new ArrayList<>();

            JSONArray resultsArray = response.getJSONArray("streams");

            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject resultsObject = resultsArray.getJSONObject(i);

                String game = resultsObject.getString("game");
                String viewers = resultsObject.getString("viewers");

                JSONObject channelJSONObject = resultsObject.getJSONObject("channel");

                // Add the incorrect answers to the incAnsArrayList
                String title = channelJSONObject.getString("status");
                String name = channelJSONObject.getString("display_name");
                String language = channelJSONObject.getString("language");
                String twitchUrl = channelJSONObject.getString("url");
                String imageUrl = channelJSONObject.getString("logo");

                // Add the information to the menu array list
                favouriteArrayList.add(new FavouritesInformation(game, title, name, viewers, language, twitchUrl, imageUrl));

                // Pass the array list back to the activity that requested it
                activity.gotFavourite(favouriteArrayList);
            }

        } catch (JSONException e) {
            // If an error occurs, print the error
            e.printStackTrace();
        }
    }
}