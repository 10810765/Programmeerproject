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

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 01/02/2019
 */
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

    // Method that will attempt to retrieve the favourite streamers from the API
    public void getFavourite(FavouritesRequest.Callback activity, String streamerNames) {
        this.activity = activity;

        // Create a new request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create a JSON object request and add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://api.twitch.tv/kraken/streams?channel="
                + streamerNames + "&client_id=43kgmi902ijvh15g5t0m3kxsjckjfn"
                ,null
                , this
                , this);
        queue.add(jsonObjectRequest);
    }

    @Override // Handle on API error response
    public void onErrorResponse(VolleyError error) {

        // If the message is not null, give back the message
        if (error.getMessage() != null) {
            activity.gotFavouriteError(error.getMessage());
            Log.d("gotMatchesError", error.getMessage());
        } else {
            activity.gotFavouriteError("Something went wrong fetching the data...");
            Log.d("gotFavouritesError", "Something went wrong fetching the data...");
        }
    }

    @Override // Handle on API response
    public void onResponse(JSONObject response) {

        try {

            // Instantiate array list
            ArrayList<FavouritesInformation> favouritesArrayList = new ArrayList<>();

            JSONArray streamsArray = response.getJSONArray("streams");

            // Loop over the JSON array and extract the strings in it
            for (int i = 0; i < streamsArray.length(); i++) {

                JSONObject streamsObject = streamsArray.getJSONObject(i);

                // Extract the game and viewer count from the streams JSON Object
                String game = streamsObject.getString("game");
                String viewers = streamsObject.getString("viewers");

                JSONObject channelJSONObject = streamsObject.getJSONObject("channel");

                // Extract the title, name, language, url and image of the channel JSON Object
                String title = channelJSONObject.getString("status");
                String name = channelJSONObject.getString("display_name");
                String language = channelJSONObject.getString("language");
                String twitchUrl = channelJSONObject.getString("url");
                String imageUrl = channelJSONObject.getString("logo");

                // Add the information to the favourites array list
                favouritesArrayList.add(new FavouritesInformation(game, title, name, viewers, language, twitchUrl, imageUrl));
            }

            // Pass the array list back to the activity that requested it
            activity.gotFavourite(favouritesArrayList);

        } catch (JSONException e) {
            // If an error occurs, print the error
            e.printStackTrace();
        }
    }
}
