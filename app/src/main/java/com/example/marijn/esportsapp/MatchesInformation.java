package com.example.marijn.esportsapp;

public class MatchesInformation {

    private String date, title, teams, eventUrl, imageUrl;

    // Matches information constructor
    public MatchesInformation(String date, String title, String teams, String eventUrl, String imageUrl) {
        this.date = date;
        this.title = title;
        this.teams = teams;
        this.eventUrl = eventUrl;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

