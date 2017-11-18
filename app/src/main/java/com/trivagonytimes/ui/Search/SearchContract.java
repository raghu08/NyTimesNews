package com.trivagonytimes.ui.Search;

import com.trivagonytimes.model.ApiResponse;

/**
 * Created by raghavendramalgi on 07/09/17.
 */

public interface SearchContract {
    interface View {

        void setProgressIndicator(boolean active);

        void showSearchedArticles(ApiResponse apiResponse);
        void loadMoreSearchedArticles(ApiResponse apiResponse);
        void failedToFetchArticles();

    }

    interface UserActionsListener {
        void  loadArticles(String q,int page);

    }
}
