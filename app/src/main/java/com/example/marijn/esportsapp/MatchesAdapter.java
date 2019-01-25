package com.example.marijn.esportsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MatchesAdapter extends ArrayAdapter<MatchesInformation> {

    public MatchesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MatchesInformation> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override // Method that will be called every time a new list item (match) is to be displayed
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        // Get the index of the match that we want to display
        MatchesInformation matchInfo = getItem(position);

        // If the convert view is null, inflate a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.match_row, parent, false);
        }

        // Get the ID's of various TextViews and an ImageView
        TextView date = convertView.findViewById(R.id.dateView);
        TextView title = convertView.findViewById(R.id.titleView);
        TextView teams = convertView.findViewById(R.id.teamsView);
        ImageView logo = convertView.findViewById(R.id.logoView);

        // Set the date, tile and teams names of the match
        date.setText(matchInfo.getDate());
        title.setText(matchInfo.getTitle());
        teams.setText(matchInfo.getTeams());

        // Load the league logo into an image view using Picasso
        Picasso.get().load(matchInfo.getImageUrl()).resize(250, 250).onlyScaleDown().into(logo);

        return convertView;
    }
}
