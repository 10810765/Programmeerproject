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
public class StreamsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;

    // Notify the activity that instantiated the request through callback
    public interface Callback {
        void gotStreams(ArrayList<StreamsInformation> streams);
        void gotStreamsError(String message);
    }

    public StreamsRequest(Context context) {
        this.context = context;
    }

    // Method that will attempt to retrieve the streams from the API
    public void getStreams(Callback activity, String game, String language, int amount) {
        this.activity = activity;

        // Create a new request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create a JSON object request and add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://api.twitch.tv/kraken/streams/?game="
                + game + "&broadcaster_language="
                + language + "&limit="
                + amount + "&client_id=43kgmi902ijvh15g5t0m3kxsjckjfn"
                ,null
                , this
                , this);
        queue.add(jsonObjectRequest);
    }

    @Override // Handle on API error response
    public void onErrorResponse(VolleyError error) {

        // If the message is not null, give back the message
        if (error.getMessage() != null) {
            activity.gotStreamsError(error.getMessage());
            Log.d("gotMatchesError", error.getMessage());
        } else {
            activity.gotStreamsError("Something went wrong fetching the data...");
            Log.d("gotStreamsError", "Something went wrong fetching the data...");
        }
    }

    @Override // Handle on API response
    public void onResponse(JSONObject response) {

        try {

            // Instantiate array list
            ArrayList<StreamsInformation> streamsArrayList = new ArrayList<>();

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

                JSONObject previewJSONObject = streamsObject.getJSONObject("preview");

                // Extract the preview url of the preview JSON Object
                String previewUrl = previewJSONObject.getString("large");

                // Add the information to the streams array list
                streamsArrayList.add(new StreamsInformation(title, game, name, viewers, language, twitchUrl, imageUrl, previewUrl));
            }

            // Pass the array list back to the activity that requested it
            activity.gotStreams(streamsArrayList);

        } catch (JSONException e) {
            // If an error occurs, print the error
            e.printStackTrace();
        }
    }
}

