package com.example.marijn.esportsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FeaturedFragment extends Fragment implements MatchesRequest.Callback, StreamsRequest.Callback {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_featured, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Make a request for the upcoming match
        MatchesRequest matchRequest = new MatchesRequest(getActivity());
        matchRequest.getMatches(this, "lol",1);

        // Make a request for the most watched streamer
        StreamsRequest streamRequest = new StreamsRequest(getActivity());
        streamRequest.getStreams(this, "League of Legends", "en", 1);
    }

    @Override // Method that handles a successful call to the API
    public void gotMatches(ArrayList<MatchesInformation> matchInf) {

        TextView date= rootView.findViewById(R.id.dateView);
        TextView title = rootView.findViewById(R.id.titleView);
        TextView url = rootView.findViewById(R.id.urlView);

        date.setText(matchInf.get(0).getDate());
        title.setText(matchInf.get(0).getTitle());
        url.setText(matchInf.get(0).getEventUrl());
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotMatchesError(String message) {
        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }

    @Override // Method that handles a successful call to the API
    public void gotStreams(ArrayList<StreamsInformation> streamInf) {

        TextView title= rootView.findViewById(R.id.streamTitle);
        TextView name = rootView.findViewById(R.id.streamerName);
        TextView views = rootView.findViewById(R.id.viewerCount);

        title.setText(streamInf.get(0).getTitle());
        name.setText(streamInf.get(0).getName());
        views.setText(streamInf.get(0).getViewers());
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotStreamsError(String message) {
        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }
}
