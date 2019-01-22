package com.example.marijn.esportsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static android.content.Context.MODE_PRIVATE;

public class FavouritesFragment extends Fragment implements FavouritesRequest.Callback {

    private View rootView;
    private List<String> favouriteStreamers = new ArrayList<>();
    private ArrayList<FavouritesInformation> favouriteInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favourites, container, false);

        SharedPreferences prefA = getContext().getSharedPreferences("favourite", MODE_PRIVATE);

        // With help from: https://stackoverflow.com/questions/35536415/
        Map<String, ?> allEntries = prefA .getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getValue().equals(true))
                // Make api request with the name
                favouriteStreamers.add(entry.getKey());
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StringJoiner joiner = new StringJoiner(",");

        for (String streamer : favouriteStreamers) {
            joiner.add(streamer);
        }

        String joinedString = joiner.toString();

        FavouritesRequest favouriteStreamRequest = new FavouritesRequest(getActivity());
        favouriteStreamRequest.getFavourite(FavouritesFragment.this, joinedString);

//        Toast.makeText(getActivity(), joinedString, Toast.LENGTH_LONG).show();

    }

    @Override // Method that handles a successful call to the API
    public void gotFavourite(ArrayList<FavouritesInformation> favouriteInf) {

        favouriteInfo = favouriteInf;

        // Instantiate the adapter
        FavouritesAdapter favouriteAdapter = new FavouritesAdapter(getActivity(), R.layout.favourite_row, favouriteInfo);

        // Get list view ID and attach the adapter to it
        ListView favouriteList = rootView.findViewById(R.id.favouriteList);
        favouriteList.setAdapter(favouriteAdapter);
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotFavouriteError(String message) {
        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }
//
//    @Override // Method that handles a successful call to the API
//    public void gotStreams(ArrayList<StreamsInformation> streamInf) {
//
//        TextView title= rootView.findViewById(R.id.streamTitle);
//        TextView name = rootView.findViewById(R.id.streamerName);
//        TextView views = rootView.findViewById(R.id.viewerCount);
//
//        title.setText(streamInf.get(0).getTitle());
//        name.setText(streamInf.get(0).getName());
//        views.setText(streamInf.get(0).getViewers());
//    }
//
//    @Override // Method that handles an unsuccessful to the the API
//    public void gotStreamsError(String message) {
//        // Toast the error message to the screen
//        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
//        Log.d("error", message);
//    }
}
