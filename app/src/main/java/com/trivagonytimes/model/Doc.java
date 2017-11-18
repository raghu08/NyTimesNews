
package com.trivagonytimes.model;

import java.util.List;

public class Doc {
    private String snippet;
    private Object _abstract;
    private List<Multimedium> multimedia = null;
    private Headline headline;
    private String pub_date;

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }

    private Byline byline;
    private String sectionName;
    public Headline getHeadline() {
        return headline;
    }
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }
    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Object getAbstract() {
        return _abstract;
    }

    public void setAbstract(Object _abstract) {
        this._abstract = _abstract;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }


    public String getPubDate() {
        return pub_date;
    }

    public void setPubDate(String pub_date) {
        this.pub_date = pub_date;
    }



    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }





}
