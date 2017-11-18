package com.trivagonytimes.repository;

import com.trivagonytimes.model.MostViewedArticleResponse;


public interface MostViewedArticleRepoListener {
     void onSuccess(MostViewedArticleResponse response);
     void onFailure();

}
