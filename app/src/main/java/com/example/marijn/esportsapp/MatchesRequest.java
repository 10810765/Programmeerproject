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

import java.util.ArrayList;

public class MatchesRequest implements Response.Listener<JSONArray>, Response.ErrorListener {
    private Context context;
    private Callback activity;

    // Notify the activity that instantiated the request through callback
    public interface Callback {
        void gotMatches(ArrayList<MatchesInformation> matches);
        void gotMatchesError(String message);
    }

    public MatchesRequest(Context context) {
        this.context = context;
    }

    // Method will attempt to retrieve the categories from the API
    public void getMatches(Callback activity, String game, int amount) {
        this.activity = activity;

        // Create a new request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create a JSON object request and add it to the queue
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://api.pandascore.co/"+game+"/matches/upcoming?page[size]="+amount+"&token=flAODiQVW9o9n8lVU1NWnZGfPLIAU9ClcrSxStPz7Wy5qZQVZOk"
                , this
                , this);
        queue.add(jsonArrayRequest);
    }

    @Override // Handle on API error response
    public void onErrorResponse(VolleyError error) {
        activity.gotMatchesError(error.getMessage());
        Log.d("gotMatchesError", error.getMessage());
    }

    @Override // Handle on API response
    public void onResponse(JSONArray response) {

        try {

            // Instantiate array list
            ArrayList<MatchesInformation> matchesArrayList = new ArrayList<>();

            for (int i = 0; i < response.length(); i++) {

                JSONObject match = response.getJSONObject(i);

                String date = match.getString("begin_at");
                String teams = match.getString("name");

                JSONObject gameJSONObject = match.getJSONObject("videogame");

                String game = gameJSONObject.getString("name");

                JSONObject leagueJSONObject = match.getJSONObject("league");

                String title = leagueJSONObject.getString("name");
                String eventUrl = leagueJSONObject.getString("url");
                String imageUrl = leagueJSONObject.getString("image_url");

                // Instantiate a second array list (used for logo's of the teams)
                ArrayList<String> teamLogosArrayList = new ArrayList<>();

                JSONArray opponentsJSONArray = match.getJSONArray("opponents");

                for (int j = 0; j < opponentsJSONArray.length(); j++) {

                    JSONObject opponentsObject = opponentsJSONArray.getJSONObject(j);

                    JSONObject opponentJSONObject = opponentsObject.getJSONObject("opponent");

                    teamLogosArrayList.add(opponentJSONObject.getString("image_url"));
                }

                matchesArrayList.add( new MatchesInformation(date, title, game, teams, eventUrl, imageUrl, teamLogosArrayList));

            }

            // Pass the list back to the activity that requested it
            activity.gotMatches(matchesArrayList);

        } catch (JSONException e) {
            // If an error occurs, print the error
            e.printStackTrace();
        }
    }
}

