package com.example.marijn.esportsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 01/02/2019
 */
public class FeaturedFragment extends Fragment implements MatchesRequest.Callback, StreamsRequest.Callback {

    private View rootView;
    private ArrayList<String> teamLogoUrls;
    private String matchUrl, streamUrl, game;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_featured, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Make an API request for the first upcoming match
        MatchesRequest matchRequest = new MatchesRequest(getActivity());
        matchRequest.getMatches(this, "",1);

        // Make an API request for the most watched streamer
        StreamsRequest streamRequest = new StreamsRequest(getActivity());
        streamRequest.getStreams(this, "", "", 1);

        // Set an on click listener for the match LinearLayout
        LinearLayout matchLayout = rootView.findViewById(R.id.matchClickable);
        matchLayout.setOnClickListener(new OnMatchClickListener());

        // Set an on click listener for the stream LinearLayout
        LinearLayout streamLayout = rootView.findViewById(R.id.streamClickable);
        streamLayout.setOnClickListener(new OnStreamClickListener());
    }

    @Override // Method that handles a successful call to the API
    public void gotMatches(ArrayList<MatchesInformation> matchInf) {

        // Store the retrieved logo's, url and game in private variables
        teamLogoUrls = matchInf.get(0).getTeamLogos();
        matchUrl = matchInf.get(0).getEventUrl();
        game = matchInf.get(0).getGame();

        // Get the ID's of various TextViews and ImageViews
        TextView titleView = rootView.findViewById(R.id.titleView);
        TextView teamsView = rootView.findViewById(R.id.teamsView);
        TextView dateView = rootView.findViewById(R.id.dateView);
        ImageView logoOneView = rootView.findViewById(R.id.teamOneView);
        ImageView logoTwoView = rootView.findViewById(R.id.teamTwoView);

        // Load the team logo's into an image view using Picasso
        Picasso.get().load(teamLogoUrls.get(0)).into(logoOneView);
        Picasso.get().load(teamLogoUrls.get(1)).into(logoTwoView);

        // Specify the default Date Format and the wanted Date Format
        // With help from: https://stackoverflow.com/questions/4216745/
        DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat newDateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");
        Date date = null;

        try {

            // Parse the match date string into Date Format
            date = defaultDateFormat.parse(matchInf.get(0).getDate());

        } catch (ParseException e) {
            // If an error occurs, print the error
            e.printStackTrace();
        }

        // Add one hour to the date to make it GMT+1
        date.setTime(date.getTime()+ 3_600_000);

        // Format the default Date Format to the desired (new) Date Format and save as string
        String formattedDateString = newDateFormat.format(date);

        // Set the title, teams and date of the match
        titleView.setText(matchInf.get(0).getTitle()+ " (" + game + ")");
        teamsView.setText(matchInf.get(0).getTeams());
        dateView.setText(formattedDateString + "  (GMT+1) ");
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotMatchesError(String message) {

        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }

    @Override // Method that handles a successful call to the API
    public void gotStreams(ArrayList<StreamsInformation> streamInf) {

        // Store the retrieved url in a private variable
        streamUrl = streamInf.get(0).getTwitchUrl();

        // Get the ID's of various TextViews and an ImageView
        TextView nameView = rootView.findViewById(R.id.nameView);
        TextView viewsView = rootView.findViewById(R.id.viewersView);
        ImageView previewView = rootView.findViewById(R.id.previewView);

        // Load the stream preview into an image view using Picasso
        Picasso.get().load(streamInf.get(0).getPreviewUrl()).into(previewView);

        // Set the name and viewer count of the stream
        nameView.setText(streamInf.get(0).getName() + " is streaming " + streamInf.get(0).getGame());
        viewsView.setText(streamInf.get(0).getViewers() + " viewers");
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotStreamsError(String message) {

        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }

    // Create an on match clicked listener
    private class OnMatchClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            // If the retrieved url to an official match of a game equals null,
            // replace it with the url of the games' official website
            if (matchUrl.equals("null")) {
                switch (game) {
                    case "LoL":
                        matchUrl = "https://euw.leagueoflegends.com/";
                        break;
                    case "ow":
                        matchUrl = "https://playoverwatch.com/";
                        break;
                    case "Dota 2":
                        matchUrl = "http://dota2.com/";
                        break;
                    case "CS:GO":
                        matchUrl = "https://blog.counter-strike.net/";
                        break;
                }
            }

            // Open the url of the clicked match in a web browser
            // https://stackoverflow.com/questions/2201917/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(matchUrl));
            startActivity(browserIntent);
        }
    }

    // Create an on stream clicked listener
    private class OnStreamClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            // Open the url of the clicked stream in a web browser
            // https://stackoverflow.com/questions/2201917/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(streamUrl));
            startActivity(browserIntent);
        }
    }
}
