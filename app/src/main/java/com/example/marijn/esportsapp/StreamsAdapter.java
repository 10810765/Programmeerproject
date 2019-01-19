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

public class StreamsAdapter extends ArrayAdapter<StreamsInformation> {

    private ArrayList streamerInfo;

    public StreamsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<StreamsInformation> objects) {
        super(context, resource, objects);
        this.streamerInfo = objects;
    }

    @NonNull
    @Override // Method that will be called every time a new list item (menu item) is to be displayed
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        StreamsInformation streamInfo = getItem(position);

        // If the convert view is null, inflate a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stream_row, parent, false);
        }

        // Get the index of the menu item that we want to display
        StreamsInformation stream = (StreamsInformation) streamerInfo.get(position);

        // Get the ID's of the name and price (TextView) and the picture (ImageView)
        TextView title = convertView.findViewById(R.id.titleView);
        TextView name = convertView.findViewById(R.id.nameView);
        TextView views = convertView.findViewById(R.id.viewersView);
        ImageView logo = convertView.findViewById(R.id.logoView);

        // Set the name and price of the dish
        title.setText(streamInfo.getTitle());
        name.setText(streamInfo.getName());
        views.setText(streamInfo.getViewers());

        // Load image from the internet into an image view using Picasso
        Picasso.get().load(stream.getImageUrl()).into(logo);

        return convertView;
    }

}
