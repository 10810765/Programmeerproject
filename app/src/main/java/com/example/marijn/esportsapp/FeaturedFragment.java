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
import java.util.Calendar;
import java.util.Date;

public class FeaturedFragment extends Fragment implements MatchesRequest.Callback, StreamsRequest.Callback {

    private View rootView;
    private ArrayList<String> teamLogoUrls;
    private String matchUrl, streamUrl, game;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

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

        teamLogoUrls = matchInf.get(0).getTeamLogos();
        matchUrl = matchInf.get(0).getEventUrl();
        game = matchInf.get(0).getGame();

        TextView title = rootView.findViewById(R.id.titleView);
        TextView teams = rootView.findViewById(R.id.teamsView);
        TextView dateView = rootView.findViewById(R.id.dateView);

        ImageView logoOne = rootView.findViewById(R.id.teamOneImage);
        ImageView logoTwo = rootView.findViewById(R.id.teamTwoImage);

        // Load image from the internet into an image view using Picasso
        Picasso.get().load(teamLogoUrls.get(0)).into(logoOne);
        Picasso.get().load(teamLogoUrls.get(1)).into(logoTwo);

        // With help from: https://stackoverflow.com/questions/4216745/
        DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat newDateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");
        Date date = null;

        try {
            date = defaultDateFormat.parse(matchInf.get(0).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Add one hour to the time to make it GMT+1
        date.setTime(date.getTime()+ 3_600_000);

        String formattedDateString = newDateFormat.format(date);

        title.setText(matchInf.get(0).getTitle()+ " (" + game + ")");
        teams.setText(matchInf.get(0).getTeams());
        dateView.setText(formattedDateString + "  (GMT+1)");
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotMatchesError(String message) {

        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }

    @Override // Method that handles a successful call to the API
    public void gotStreams(ArrayList<StreamsInformation> streamInf) {

        streamUrl = streamInf.get(0).getTwitchUrl();

        TextView name = rootView.findViewById(R.id.streamerName);
        TextView views = rootView.findViewById(R.id.viewerCount);
        ImageView preview = rootView.findViewById(R.id.previewImage);

        // Load image from the internet into an image view using Picasso
        Picasso.get().load(streamInf.get(0).getPreviewUrl()).into(preview);

        name.setText(streamInf.get(0).getName() + " is streaming " + streamInf.get(0).getGame());
        views.setText("Viewers: " + streamInf.get(0).getViewers());
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotStreamsError(String message) {
        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }

    private class OnMatchClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

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

            // https://stackoverflow.com/questions/2201917/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(matchUrl));
            startActivity(browserIntent);
        }
    }

    private class OnStreamClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            // https://stackoverflow.com/questions/2201917/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(streamUrl));
            startActivity(browserIntent);
        }
    }
}
