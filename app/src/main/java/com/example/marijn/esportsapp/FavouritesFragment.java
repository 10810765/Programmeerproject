package com.example.marijn.esportsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static android.content.Context.MODE_PRIVATE;

public class FavouritesFragment extends Fragment implements FavouritesRequest.Callback {

    private View rootView;
    private List<String> favouriteStreams = new ArrayList<>();
    private ArrayList<FavouritesInformation> favouriteInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favourites, container, false);

        // Get the previously stored favourite streams
        SharedPreferences favouritePrefs = getContext().getSharedPreferences("favourite", MODE_PRIVATE);

        // Get all the keys (streamer names) and values (boolean) from favouritePrefs
        // With help from: https://stackoverflow.com/questions/35536415/
        Map<String, ?> allFavourites = favouritePrefs.getAll();

        // Loop through all the key, value pairs
        for (Map.Entry<String, ?> favourite : allFavourites.entrySet()) {

            // If a key has a value of true store that key in a list
            if (favourite.getValue().equals(true))
                favouriteStreams.add(favourite.getKey());
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instantiate a string joiner that separates the strings with a comma
        StringJoiner streamerNameJoiner = new StringJoiner(",");

        // Loop through the favouriteStreams list and join them together using the StringJoiner
        for (String streamer : favouriteStreams) {
            streamerNameJoiner.add(streamer);
        }

        // Turn the StringJoiner into one long string (of favourite streamer names)
        // Note: This is necessary for the API call
        String favouriteStreamerNames = streamerNameJoiner.toString();

        // Make an API request with all favouriteStreamerNames
        FavouritesRequest favouriteStreamRequest = new FavouritesRequest(getActivity());
        favouriteStreamRequest.getFavourite(FavouritesFragment.this, favouriteStreamerNames);

        // Instantiate an on favourite stream click listener
        ListView favouriteStreamsList = rootView.findViewById(R.id.favouritesList);
        favouriteStreamsList.setOnItemClickListener(new FavouritesFragment.FavouriteClickListener());
    }

    @Override // Method that handles a successful call to the API
    public void gotFavourite(ArrayList<FavouritesInformation> favouriteInf) {

        favouriteInfo = favouriteInf;

        // If statement to make sure the app doesn't crash when a fragment is clicked multiple times
        // With help from: https://stackoverflow.com/questions/39532507/
        if (getActivity() != null) {

            // Instantiate the favourites adapter
            FavouritesAdapter favouriteAdapter = new FavouritesAdapter(getActivity(), R.layout.favourite_row, favouriteInfo);

            // Get favourites list view ID and attach the adapter to it
            ListView favouriteList = rootView.findViewById(R.id.favouritesList);
            favouriteList.setAdapter(favouriteAdapter);
        }
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotFavouriteError(String message) {

        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }

    // Create an on favourite stream clicked listener
    private class FavouriteClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get the position of the clicked favourite stream (to determine which one was clicked)
            FavouritesInformation clickedFavouriteStream = favouriteInfo.get(position);

            // Get the url of the clicked favourite stream
            String urlToStream = clickedFavouriteStream.getTwitchUrl();

            // Open the url of the clicked favourite stream in a web browser
            // https://stackoverflow.com/questions/2201917/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToStream));
            startActivity(browserIntent);
        }
    }
}
