package com.trivagonytimes.ui.Home;

import com.trivagonytimes.model.MostViewedArticleResponse;



public interface MostViewedArticlesContract {

    interface View {

        void setProgressIndicator(boolean active);
        void loadMostViewedArticles(MostViewedArticleResponse response);
        void failedToFetchArticles();


    }

    interface UserActionsListener {
        void  getMostViewedArticles();

    }
}
