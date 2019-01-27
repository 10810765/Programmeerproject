package com.example.marijn.esportsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 01/02/2019
 */
public class MatchesFragment extends Fragment implements MatchesRequest.Callback {

    private View rootView;
    private ArrayList<MatchesInformation> matchInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instantiate the games adapter (for the spinners)
        // With help from: https://stackoverflow.com/questions/13377361/
        ArrayAdapter<String> gamesAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.games));

        // Get the ID of the games spinner and attach the adapter to it
        Spinner gamesSpinner = rootView.findViewById(R.id.selectGameSpinner);
        gamesSpinner.setAdapter(gamesAdapter);

        // Get a previously stored selected game
        SharedPreferences prefs = getContext().getSharedPreferences("matchesFragment", MODE_PRIVATE);
        int storedGame = prefs.getInt("game", 0);

        // Set the previously selected game
        gamesSpinner.setSelection(storedGame);

        // Instantiate an on game selected listener
        gamesSpinner.setOnItemSelectedListener(new OnGameSelectedListener());

        // Instantiate an on match click listener
        ListView listView = rootView.findViewById(R.id.matchesList);
        listView.setOnItemClickListener(new MatchesFragment.MatchClickListener());
    }

    @Override // Method that handles a successful call to the API
    public void gotMatches(ArrayList<MatchesInformation> matchInf) {

        // Store the retrieved match information in a private variable
        matchInfo = matchInf;

        // If statement to make sure the app doesn't crash when a fragment is clicked multiple times
        // With help from: https://stackoverflow.com/questions/39532507/
        if (getActivity() != null) {

            // Instantiate the matches adapter
            MatchesAdapter matchAdapter = new MatchesAdapter(getActivity(), R.layout.match_row, matchInf);

            // Get matches list view ID and attach the adapter to it
            ListView matchesList = rootView.findViewById(R.id.matchesList);
            matchesList.setAdapter(matchAdapter);
        }
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotMatchesError(String message) {

        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    // Create an on game selected listener
    private class OnGameSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            // Get the position of the selected game (to determine which game was selected)
            String selectedItem = parent.getItemAtPosition(position).toString();

            // Instantiate a matches API request
            MatchesRequest matchRequest = new MatchesRequest(getActivity());

            // Replace the full name of the selected game with the acroym and make an API request for that game
            switch (selectedItem) {
                case "League of Legends":
                    matchRequest.getMatches(MatchesFragment.this, "lol", 20);
                    break;
                case "Overwatch":
                    matchRequest.getMatches(MatchesFragment.this, "ow", 20);
                    break;
                case "Dota 2":
                    matchRequest.getMatches(MatchesFragment.this, "dota2", 20);
                    break;
                case "Counter-Strike: Global Offensive":
                    matchRequest.getMatches(MatchesFragment.this, "csgo", 20);
                    break;
            }

            // Edit the old selected game value (position) and store the new value
            SharedPreferences.Editor editor = getContext().getSharedPreferences("matchesFragment", MODE_PRIVATE).edit();
            editor.putInt("game", position);
            editor.apply();
        }

        // If no game selected do nothing (return)
        public void onNothingSelected(AdapterView<?> parent) {
            return;
        }
    }

    // Create an on match clicked listener
    private class MatchClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get the position of the clicked match (to determine which match was clicked)
            MatchesInformation clickedMatch = matchInfo.get(position);

            // Retrieve the url and game of the clicked match
            String urlToMatch = clickedMatch.getEventUrl();
            String game = clickedMatch.getGame();

            // If the retrieved url to an official match of a game equals null,
            // replace it with the url of the games' official website
            if (urlToMatch.equals("null")) {
                switch (game) {
                    case "LoL":
                        urlToMatch = "https://euw.leagueoflegends.com/";
                        break;
                    case "ow":
                        urlToMatch = "https://playoverwatch.com/";
                        break;
                    case "Dota 2":
                        urlToMatch = "http://dota2.com/";
                        break;
                    case "CS:GO":
                        urlToMatch = "https://blog.counter-strike.net/";
                        break;
                }
            }

            // Open the url of the clicked favourite stream in a web browser
            // https://stackoverflow.com/questions/2201917/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToMatch));
            startActivity(browserIntent);
        }
    }
}
