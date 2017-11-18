package com.trivagonytimes.ui.Search;

import com.trivagonytimes.model.ApiResponse;
import com.trivagonytimes.repository.NyTimesRepository;
import com.trivagonytimes.repository.SearchRepoListener;

/**
 * Created by raghavendramalgi on 04/08/17.
 */

public class SearchArticlesPresenter implements SearchContract.UserActionsListener,SearchRepoListener {

    private final SearchContract.View  view;
    private NyTimesRepository nyTimesRepo;
    private int tempPage;

    public SearchArticlesPresenter(SearchContract.View view) {
        nyTimesRepo = new NyTimesRepository();
        this.view = view;
    }


    public void loadArticles(String q,int page) {
       // if(q.length()>0) {
            view.setProgressIndicator(true);
            tempPage = page;
            nyTimesRepo.triggerSearchQuery(q, page, this);
        //}

    }


    @Override
    public void onSuccess(ApiResponse response) {
        view.setProgressIndicator(false);
        if(tempPage>0){
            view.loadMoreSearchedArticles(response);
        }else{
            view.showSearchedArticles(response);
        }

    }

    @Override
    public void onFailure() {
        view.setProgressIndicator(false);
        view.failedToFetchArticles();
    }
}
