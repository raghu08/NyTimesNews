package com.trivagonytimes.repository;

import android.util.Log;

import com.trivagonytimes.api.NyTimesApiService;
import com.trivagonytimes.model.ApiResponse;
import com.trivagonytimes.model.MostViewedArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NyTimesRepository {
    MostViewedArticleResponse res = null;


    public void triggerSearchQuery(String q,int page,SearchRepoListener listener){
        NyTimesApiService apiService = RetrofitClient.getInstance().getNyTimesApiService();
        Call<ApiResponse> call = apiService.searchQueryApi(q,page);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                listener.onSuccess(response.body());
            }
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                listener.onFailure();
            }

        });

    }


    public void mostViewed(MostViewedArticleRepoListener listener){
        NyTimesApiService apiService = RetrofitClient.getInstance().getNyTimesApiService();
        Call<String> call = apiService.getMostPopularArticles(RetrofitClient.MOST_VIEWED_URL);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                res = new MostViewedArticleResponse(response.body());
                listener.onSuccess(res.parseData());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFailure();
            }
        });

    }





}
