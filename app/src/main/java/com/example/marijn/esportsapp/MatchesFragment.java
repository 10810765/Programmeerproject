package com.example.marijn.esportsapp;

import android.content.Intent;
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

        // Instantiate an on list item click listener
        ListView listView = rootView.findViewById(R.id.matchList);
        listView.setOnItemClickListener(new MatchesFragment.ItemClickListener());

        Spinner mySpinner = rootView.findViewById(R.id.selectGameSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.games));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Make a request for the top 20 streams
                MatchesRequest matchRequest = new MatchesRequest(getActivity());

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

            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }

    @Override // Method that handles a successful call to the API
    public void gotMatches(ArrayList<MatchesInformation> matchInf) {

        matchInfo = matchInf;

        // Instantiate the adapter
        MatchesAdapter matchAdapter = new MatchesAdapter(getActivity(), R.layout.match_row, matchInf);

        // Get list view ID and attach the adapter to it
        ListView matchList = rootView.findViewById(R.id.matchList);
        matchList.setAdapter(matchAdapter);
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotMatchesError(String message) {
        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    // Create an on menu item clicked listener
    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get the MenuItem object of the clicked item in the list view
            MatchesInformation clickedMatch = matchInfo.get(position);

            // Put menu item information into the bundle
            String urlToMatch = clickedMatch.getEventUrl();
            String game = clickedMatch.getGame();

            if (urlToMatch.equals("")) {

                switch (game) {
                    case "LoL":
                        urlToMatch = "https://play.euw.leagueoflegends.com/en_GB";
                        break;
                    case "Ow":
                        urlToMatch = "https://playoverwatch.com/en-us/";
                        break;
                    case "dota2":
                        urlToMatch = "http://blog.dota2.com/?l=english";
                        break;
                    case "csgo":
                        urlToMatch = "https://blog.counter-strike.net/";
                        break;
                }
            }

            // https://stackoverflow.com/questions/2201917/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToMatch));
            startActivity(browserIntent);
        }
    }
}
