package com.example.marijn.esportsapp;

public class StreamsInformation {

    private String title, name, viewers, language, imageUrl;

    // Matches information constructor
    public StreamsInformation(String title, String name, String viewers, String language, String imageUrl) {
        this.title = title;
        this.name = name;
        this.viewers = viewers;
        this.language = language;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
