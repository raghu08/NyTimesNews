package com.trivagonytimes.ui.Home;

import com.trivagonytimes.model.MostViewedArticleResponse;
import com.trivagonytimes.repository.MostViewedArticleRepoListener;
import com.trivagonytimes.repository.NyTimesRepository;



public class MostViewedArticlesPresenter implements MostViewedArticlesContract.UserActionsListener,MostViewedArticleRepoListener {

    private NyTimesRepository nyTimesRepo;
    private final MostViewedArticlesContract.View  view;

    public MostViewedArticlesPresenter(MostViewedArticlesContract.View view) {
        nyTimesRepo = new NyTimesRepository();
        this.view = view;
    }




    @Override
    public void getMostViewedArticles()
    {
        view.setProgressIndicator(true);
        nyTimesRepo.mostViewed(this);
    }

    @Override
    public void onSuccess(MostViewedArticleResponse response) {
        view.setProgressIndicator(false);
        view.loadMostViewedArticles(response);
    }

    @Override
    public void onFailure() {
        view.setProgressIndicator(false);
        view.failedToFetchArticles();
    }
}
