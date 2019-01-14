package com.example.marijn.esportsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MatchesAdapter extends ArrayAdapter<MatchesInformation> {

    private ArrayList matches;

    public MatchesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MatchesInformation> objects) {
        super(context, resource, objects);
        this.matches = objects;
    }

    @NonNull
    @Override // Method that will be called every time a new list item (menu item) is to be displayed
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        // If the convert view is null, inflate a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.match_row, parent, false);
        }

        // Get the ID's of the name and price (TextView) and the picture (ImageView)
        TextView date = convertView.findViewById(R.id.dateView);
        TextView title = convertView.findViewById(R.id.titleView);
        TextView teams = convertView.findViewById(R.id.teamsView);
        TextView link = convertView.findViewById(R.id.urlView);

        // Get the index of the menu item that we want to display
        MatchesInformation matchInfo = (MatchesInformation) matches.get(position);

        // Set the name and price of the dish
        date.setText(matchInfo.getDate());
        title.setText(matchInfo.getTitle());
        teams.setText(matchInfo.getTeams());
        link.setText(matchInfo.getEventUrl());

        return convertView;
    }
}
