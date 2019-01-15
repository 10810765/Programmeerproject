package com.example.marijn.esportsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StreamsActivity extends AppCompatActivity implements StreamsRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streams);

        // Make a request for the top 20 streams
        StreamsRequest streamRequest = new StreamsRequest(this);
        streamRequest.getStreams(this, 20);
    }

    @Override // Method that handles a successful call to the API
    public void gotStreams(ArrayList<StreamsInformation> streamInf) {

        // Instantiate the adapter
        StreamsAdapter streamAdapter = new StreamsAdapter(this, R.layout.stream_row, streamInf);

        // Get list view ID and attach the adapter to it
        ListView streamList = findViewById(R.id.streamList);
        streamList.setAdapter(streamAdapter);
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotStreamsError(String message) {
        // Toast the error message to the screen
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
