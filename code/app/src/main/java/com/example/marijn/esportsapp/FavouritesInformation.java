package com.example.marijn.esportsapp;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 01/02/2019
 */
public class FavouritesInformation {

    private String game, title, name, viewers, language, twitchUrl, imageUrl;

    // Favourite streams information constructor
    public FavouritesInformation(String game, String title, String name, String viewers, String language, String twitchUrl, String imageUrl) {
        this.game = game;
        this.title = title;
        this.name = name;
        this.viewers = viewers;
        this.language = language;
        this.twitchUrl = twitchUrl;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getViewers() {
        return viewers;
    }

    public void setViewers(String viewers) {
        this.viewers = viewers;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTwitchUrl() {
        return twitchUrl;
    }

    public void setTwitchUrl(String twitchUrl) {
        this.twitchUrl = twitchUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
