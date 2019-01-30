package com.example.marijn.esportsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 01/02/2019
 */
public class StreamsAdapter extends ArrayAdapter<StreamsInformation> {

    public StreamsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<StreamsInformation> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override // Method that will be called every time a new list item (streamer) is to be displayed
    public View getView(final int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        // Get the index of the stream that we want to display
        StreamsInformation streamInfo = getItem(position);

        // If the convert view is null, inflate a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stream_row, parent, false);
        }

        // Get the ID's of various TextViews an ImageView and a ToggleButton
        TextView titleView = convertView.findViewById(R.id.titleView);
        TextView nameView = convertView.findViewById(R.id.nameView);
        TextView viewersView = convertView.findViewById(R.id.viewersView);
        ImageView logoView = convertView.findViewById(R.id.logoView);
        ToggleButton favouriteButton = convertView.findViewById(R.id.favouriteButton);

        // Set the name, title and viewer count of the streamer
        titleView.setText(streamInfo.getTitle());
        nameView.setText(streamInfo.getName());
        viewersView.setText(streamInfo.getViewers() + " viewers");

        // Load the streamers' logo into an image view using Picasso
        Picasso.get().load(streamInfo.getImageUrl())
                .resize(250, 250)
                .onlyScaleDown()
                .into(logoView);

        // Get the previously stored favourite button state (boolean)
        SharedPreferences favouritePrefs = getContext().getSharedPreferences("favourite", MODE_PRIVATE);
        Boolean isFavourite = favouritePrefs.getBoolean(streamInfo.getName(), false);

        // Restore the stored favourite button state
        if (isFavourite) { // If this person was a favourite
            favouriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.yellow_star));

        } else { // If this person was NOT a favourite
            favouriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.grey_star));
        }

        // Store the name of the streamer as a tag
        favouriteButton.setTag(streamInfo.getName());

        // Set an on check change listener for the favourite button
        favouriteButton.setOnCheckedChangeListener(new OnFavouriteChangeListener());

        return convertView;
    }

    // Create an on favourite button click listener
    private class OnFavouriteChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

            // Get the button that was clicked
            ToggleButton clickedFavouriteButton = (ToggleButton) buttonView;

            if (isChecked) { // If favourited
                clickedFavouriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.yellow_star));
            } else { // If unfavourited
                clickedFavouriteButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.grey_star));
            }

            // Get the streamers' name (used to store the Boolean value)
            String streamerName = (String) buttonView.getTag();

            // Edit the old Boolean value and store the new value
            SharedPreferences.Editor editor = getContext().getSharedPreferences("favourite", MODE_PRIVATE).edit();
            editor.putBoolean(streamerName, isChecked);
            editor.apply();
        }
    }
}