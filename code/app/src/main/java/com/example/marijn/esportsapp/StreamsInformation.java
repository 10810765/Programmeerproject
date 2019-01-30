package com.example.marijn.esportsapp;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 01/02/2019
 */
public class StreamsInformation {

    private String title, game,  name, viewers, language, twitchUrl, imageUrl, previewUrl;

    // Streams information constructor
    public StreamsInformation(String title, String game, String name, String viewers, String language, String twitchUrl, String imageUrl, String previewUrl) {
        this.title = title;
        this.game = game;
        this.name = name;
        this.viewers = viewers;
        this.language = language;
        this.twitchUrl = twitchUrl;
        this.imageUrl = imageUrl;
        this.previewUrl = previewUrl;
    }

    // Getters and setters
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

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}
