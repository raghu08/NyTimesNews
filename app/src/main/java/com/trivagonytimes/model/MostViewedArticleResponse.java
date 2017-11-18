package com.trivagonytimes.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/*Employed Json parsing instead of gson for MostViewed api,as I was unable to parse fields like
"abstract" and "media-metadata" using gson*/
public class MostViewedArticleResponse {
    //Json Name Value pairs
    private static final String RESULTS = "results";
    private static final String TITLE = "title";
    private static final String SECTION = "section";
    private static final String ABSTRACT = "abstract";
    private static final String BYLINE = "byline";
    private static final String PUBLISHED_DATE = "published_date";
    private static final String MEDIA = "media";
    private static final String MEDIA_METADATA = "media-metadata";
    private static final String URL = "url";

    private List<Results> results = new ArrayList<>();
    private String body;
    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public MostViewedArticleResponse(String body){
        this.body =body;
    }

    public MostViewedArticleResponse parseData(){
        try {
            JSONObject jsonObj = new JSONObject(body);
            if(jsonObj.has(RESULTS)){
                JSONArray array = jsonObj.getJSONArray(RESULTS);
                if(jsonObj.has(RESULTS)) {
                    for (int i=0;i<array.length();i++){
                        Results result = new Results();
                        JSONObject c = array.getJSONObject(i);
                        result.setSection(c.getString(SECTION));
                        result.setAbstractArticle(c.getString(ABSTRACT));
                        result.setByline(c.getString(BYLINE));
                        result.setTitle(c.getString(TITLE));
                        result.setPublished_date(c.getString(PUBLISHED_DATE));
                        results.add(result);
                        JSONArray media =  c.optJSONArray(MEDIA);
                        if(media !=null) {
                            for (int j = 0; j < media.length(); j++) {
                                Media m = new Media();
                                JSONObject metadata = media.getJSONObject(j);
                                JSONArray mediameta = metadata.optJSONArray(MEDIA_METADATA);
                                m.setUrl(mediameta.getJSONObject(0).getString(URL));
                                result.setMedia(m);
                                results.set(i,result);
                            }
                        }
                    }

                }
            }
        }


        catch(JSONException e) {
            e.printStackTrace();
        }

        return this;
    }





}
