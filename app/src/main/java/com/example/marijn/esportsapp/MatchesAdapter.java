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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 01/02/2019
 */
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
        TextView dateView = convertView.findViewById(R.id.dateView);
        TextView title = convertView.findViewById(R.id.titleView);
        TextView teams = convertView.findViewById(R.id.teamsView);
        ImageView logo = convertView.findViewById(R.id.logoView);

        // With help from: https://stackoverflow.com/questions/4216745/
        DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat newDateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");
        Date date = null;

        try {
            date = defaultDateFormat.parse(matchInfo.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Add one hour to the time to make it GMT+1
        date.setTime(date.getTime()+ 3_600_000);

        String formattedDateString = newDateFormat.format(date);

        // Set the date, tile and teams names of the match
        dateView.setText(formattedDateString + "  (GMT+1)");
        title.setText(matchInfo.getTitle());
        teams.setText(matchInfo.getTeams());

        // Load the league logo into an image view using Picasso
        Picasso.get().load(matchInfo.getImageUrl()).resize(250, 250).onlyScaleDown().into(logo);

        return convertView;
    }
}
