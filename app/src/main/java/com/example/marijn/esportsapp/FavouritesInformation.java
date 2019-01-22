package com.example.marijn.esportsapp;

public class FavouritesInformation {

    private String game, title, name, viewers, language, twitchUrl, imageUrl;

    // Matches information constructor
    public FavouritesInformation(String game, String title, String name, String viewers, String language, String twitchUrl, String imageUrl) {
        this.game = game;
        this.title = title;
        this.name = name;
        this.viewers = viewers;
        this.language = language;
        this.twitchUrl = twitchUrl;
        this.imageUrl = imageUrl;
    }

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
