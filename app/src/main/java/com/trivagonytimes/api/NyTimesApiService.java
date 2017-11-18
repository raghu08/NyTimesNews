package com.trivagonytimes.api;


import com.trivagonytimes.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface NyTimesApiService {
    @GET("articlesearch.json")
    Call<ApiResponse> searchQueryApi(
            @Query("q") String query,
            @Query("page") Integer page);
    @GET
    Call<String> getMostPopularArticles(@Url String url);


}
