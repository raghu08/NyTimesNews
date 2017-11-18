package com.trivagonytimes.repository;

import com.trivagonytimes.model.ApiResponse;


public interface SearchRepoListener {
     void onSuccess(ApiResponse response);
    void onFailure();

}
