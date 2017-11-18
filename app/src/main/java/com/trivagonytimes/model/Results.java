package com.trivagonytimes.model;


public class Results {
    private String byline;
    private String title;
    private String abstractArticle;

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    private String published_date;
    private Media media;
    private String section;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }



    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractArticle() {
        return abstractArticle;
    }

    public void setAbstractArticle(String abstractArticle) {
        this.abstractArticle = abstractArticle;
    }



}
