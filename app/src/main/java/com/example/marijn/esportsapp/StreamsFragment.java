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

        // Instantiate an on list item click listener
        ListView listView = rootView.findViewById(R.id.streamList);
        listView.setOnItemClickListener(new ItemClickListener());

        Spinner mySpinner = rootView.findViewById(R.id.selectGameSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.streamedGames));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Spinner mySecondSpinner = rootView.findViewById(R.id.selectLanguageSpinner);

        ArrayAdapter<String> mySecondAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.languages));

        mySecondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySecondSpinner.setAdapter(mySecondAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGame = parent.getItemAtPosition(position).toString();

                // Make a request for the top 20 streams
                StreamsRequest streamRequest = new StreamsRequest(getActivity());
                streamRequest.getStreams(StreamsFragment.this, selectedGame, selectedLanguage, 20);


            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        mySecondSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLanguage = parent.getItemAtPosition(position).toString();

                // Make a request for the top 20 streams
                StreamsRequest streamRequest = new StreamsRequest(getActivity());

                if (selectedLanguage.equals("ANY")) {

                    selectedLanguage = "";
                    streamRequest.getStreams(StreamsFragment.this, selectedGame, selectedLanguage, 20);

                } else {
                    streamRequest.getStreams(StreamsFragment.this, selectedGame, selectedLanguage, 20);
                }

            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }

    @Override // Method that handles a successful call to the API
    public void gotStreams(ArrayList<StreamsInformation> streamInf) {

        streamInfo = streamInf;

        // Instantiate the adapter
        StreamsAdapter streamAdapter = new StreamsAdapter(getActivity(), R.layout.stream_row, streamInf);

        // Get list view ID and attach the adapter to it
        ListView streamList = rootView.findViewById(R.id.streamList);
        streamList.setAdapter(streamAdapter);
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotStreamsError(String message) {
        // Toast the error message to the screen
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    // Create an on menu item clicked listener
    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Get the MenuItem object of the clicked item in the list view
            StreamsInformation clickedStream = streamInfo.get(position);

            // Put menu item information into the bundle
            String urlToStream = clickedStream.getTwitchUrl();

            // https://stackoverflow.com/questions/2201917/
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToStream));
            startActivity(browserIntent);
        }
    }
}
