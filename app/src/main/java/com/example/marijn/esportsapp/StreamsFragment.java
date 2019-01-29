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
public class StreamsFragment extends Fragment implements StreamsRequest.Callback {

    private View rootView;
    private ArrayList<StreamsInformation> streamInfo;
    private String selectedGame = "League of legends";
    private String selectedLanguage = "EN";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_streams, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instantiate the games and languages adapters (for the spinners)
        // With help from: https://stackoverflow.com/questions/13377361/
        ArrayAdapter<String> gamesAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.streamedGames));
        ArrayAdapter<String> languagesAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.languages));

        // Get the ID of the games spinner and attach the adapter to it
        Spinner gamesSpinner = rootView.findViewById(R.id.selectGameSpinner);
        gamesSpinner.setAdapter(gamesAdapter);

        // Get the ID of the languages spinner and attach the adapter to it
        Spinner languagesSpinner = rootView.findViewById(R.id.selectLanguageSpinner);
        languagesSpinner.setAdapter(languagesAdapter);

        // Get the previously stored selected game and language
        SharedPreferences prefs = getContext().getSharedPreferences("streamsFragment", MODE_PRIVATE);
        int storedGame = prefs.getInt("game", 0);
        int storedLanguage = prefs.getInt("language", 0);

        // Set the previously selected game and language
        gamesSpinner.setSelection(storedGame);
        languagesSpinner.setSelection(storedLanguage);

        // Instantiate an on game selected and an on language selected listener
        gamesSpinner.setOnItemSelectedListener(new OnGameSelectedListener());
        languagesSpinner.setOnItemSelectedListener(new OnLanguageSelectedListener());

        // Instantiate an on stream click listener
        ListView listView = rootView.findViewById(R.id.streamsList);
        listView.setOnItemClickListener(new StreamClickListener());
    }

    @Override // Method that handles a successful call to the API
    public void gotStreams(ArrayList<StreamsInformation> streamInf) {

        // Store the retrieved stream information in a private variable
        streamInfo = streamInf;

        // If statement to make sure the app doesn't crash when a fragment is clicked multiple times
        // With help from: https://stackoverflow.com/questions/39532507/
        if (getActivity() != null) {

            // Instantiate the streams adapter
            StreamsAdapter streamAdapter = new StreamsAdapter(getActivity(), R.layout.stream_row, streamInf);

            // Get streams list view ID and attach the adapter to it
            ListView streamsList = rootView.findViewById(R.id.streamsList);
            streamsList.setAdapter(streamAdapter);
        }
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotStreamsError(String message) {

        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    // Create an on game select listener
    private class OnGameSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            // Get the position of the selected game (to determine which game was selected)
            selectedGame = parent.getItemAtPosition(position).toString();

            // Make a request for the top 20 (currently most watched) streams
            StreamsRequest streamRequest = new StreamsRequest(getActivity());
            streamRequest.getStreams(StreamsFragment.this, selectedGame, selectedLanguage, 20);

            // Edit the old selected game value (position) and store the new value
            SharedPreferences.Editor editor = getContext().getSharedPreferences("streamsFragment", MODE_PRIVATE).edit();
            editor.putInt("game", position);
            editor.apply();
        }

        // If no game selected do nothing (return)
        public void onNothingSelected(AdapterView<?> parent) {
            return;
        }
    }

    // Create an on language select listener
    private class OnLanguageSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            // Get the position of the selected language (to determine which language was selected)
            selectedLanguage = parent.getItemAtPosition(position).toString();

            // Instantiate a streams API request
            StreamsRequest streamRequest = new StreamsRequest(getActivity());

            // If the selected language equals any, replace it with an empty string (necessary for API call)
            if (selectedLanguage.equals("ANY")) {
                selectedLanguage = "";

                // Make a request for the top 20 (currently most watched) streams
                streamRequest.getStreams(StreamsFragment.this, selectedGame, selectedLanguage, 20);

            } else {

                // Make a request for the top 20 (currently most watched) streams
                streamRequest.getStreams(StreamsFragment.this, selectedGame, selectedLanguage, 20);
            }

            // Edit the old selected language value (position) and store the new value
            SharedPreferences.Editor editor = getContext().getSharedPreferences("streamsFragment", MODE_PRIVATE).edit();
            editor.putInt("language", position);
            editor.apply();
        }

        // If no language selected do nothing (return)
        public void onNothingSelected(AdapterView<?> parent) {
            return;
        }
    }

    // Create an on stream click listener
    private class StreamClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get the position of the clicked stream (to determine which stream was clicked)
            StreamsInformation clickedStream = streamInfo.get(position);

            // Retrieve the url of the clicked stream
            String urlToStream = clickedStream.getTwitchUrl();

            // Open the url of the clicked favourite stream in a web browser
            // https://stackoverflow.com/questions/2201917/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToStream));
            startActivity(browserIntent);
        }
    }
}
