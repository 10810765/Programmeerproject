package com.example.marijn.esportsapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StreamsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;

    // Notify the activity that instantiated the request through callback
    public interface Callback {
        void gotStreams(StreamsInformation streams);
        void gotStreamsError(String message);
    }

    public StreamsRequest(Context context) {
        this.context = context;
    }

    // Method will attempt to retrieve the categories from the API
    public void getStreams(Callback activity) {
        this.activity = activity;

        // Create a new request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create a JSON object request and add it to the queue
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://api.twitch.tv/kraken/streams/?game=league%20of%20legends&limit=1&client_id=43kgmi902ijvh15g5t0m3kxsjckjfn",null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override // Handle on API error response
    public void onErrorResponse(VolleyError error) {
        activity.gotStreamsError(error.getMessage());
        Log.d("gotStreamsError", error.getMessage());
    }

    @Override // Handle on API response
    public void onResponse(JSONObject response) {

        try {

            JSONArray resultsArray = response.getJSONArray("streams");

            JSONObject resultsObject = resultsArray.getJSONObject(0);

            String viewers = resultsObject.getString("viewers");

            JSONArray channelJSONArray = resultsObject.getJSONArray("channel");

            // Add the incorrect answers to the incAnsArrayList
            String title = channelJSONArray.getString(0);
            String name = channelJSONArray.getString(0);
            String language = channelJSONArray.getString(0);
            String imageUrl = channelJSONArray.getString(0);

            // Add the information to the menu array list
            StreamsInformation streams = new StreamsInformation(title, name, viewers, language, imageUrl);

            // Pass the array list back to the activity that requested it
            activity.gotStreams(streams);

        } catch (JSONException e) {
            // If an error occurs, print the error
            e.printStackTrace();
        }
    }
}

