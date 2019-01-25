package com.example.marijn.esportsapp;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

        // Make a request for the upcoming match
        MatchesRequest matchRequest = new MatchesRequest(getActivity());
        matchRequest.getMatches(this, "",1);

        // Make a request for the most watched streamer
        StreamsRequest streamRequest = new StreamsRequest(getActivity());
        streamRequest.getStreams(this, "", "", 1);

        LinearLayout matchLayout = rootView.findViewById(R.id.matchClickable);
        matchLayout.setOnClickListener(new View.OnClickListener() {
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
        });

        LinearLayout streamLayout = rootView.findViewById(R.id.streamClickable);
        streamLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // https://stackoverflow.com/questions/2201917/
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(streamUrl));
                startActivity(browserIntent);
            }
        });
    }

    @Override // Method that handles a successful call to the API
    public void gotMatches(ArrayList<MatchesInformation> matchInf) {

        teamLogoUrls = matchInf.get(0).getTeamLogos();
        matchUrl = matchInf.get(0).getEventUrl();
        game = matchInf.get(0).getGame();

        TextView title = rootView.findViewById(R.id.titleView);
        TextView teams = rootView.findViewById(R.id.teamsView);
        TextView date = rootView.findViewById(R.id.dateView);

        ImageView logoOne = rootView.findViewById(R.id.teamOneImage);
        ImageView logoTwo = rootView.findViewById(R.id.teamTwoImage);

        // Load image from the internet into an image view using Picasso
        Picasso.get().load(teamLogoUrls.get(0)).into(logoOne);
        Picasso.get().load(teamLogoUrls.get(1)).into(logoTwo);

        title.setText(matchInf.get(0).getTitle()+ " (" + game + ")");
        teams.setText(matchInf.get(0).getTeams());
        date.setText(matchInf.get(0).getDate());
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
}
