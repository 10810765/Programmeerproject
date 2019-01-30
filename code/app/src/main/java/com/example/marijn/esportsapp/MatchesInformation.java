package com.example.marijn.esportsapp;

import java.util.ArrayList;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 01/02/2019
 */
public class MatchesInformation {

    private String date, title, game, teams, eventUrl, imageUrl;
    private ArrayList teamLogos;

    // Matches information constructor
    public MatchesInformation(String date, String title, String game, String teams, String eventUrl, String imageUrl, ArrayList teamLogos) {
        this.date = date;
        this.title = title;
        this.game = game;
        this.teams = teams;
        this.eventUrl = eventUrl;
        this.imageUrl = imageUrl;
        this.teamLogos = teamLogos;
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

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
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

    public ArrayList getTeamLogos() {
        return teamLogos;
    }

    public void setTeamLogos(ArrayList teamLogos) {
        this.teamLogos = teamLogos;
    }
}

