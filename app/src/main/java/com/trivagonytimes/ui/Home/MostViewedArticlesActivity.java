package com.trivagonytimes.ui.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.trivagonytimes.R;
import com.trivagonytimes.custom.SimpleDividerItemDecoration;
import com.trivagonytimes.model.MostViewedArticleResponse;
import com.trivagonytimes.model.Results;
import com.trivagonytimes.ui.Home.adapter.MostViewedArticleAdapter;
import com.trivagonytimes.ui.Search.SearchArticlesActivity;

import java.util.ArrayList;
import java.util.List;

public class MostViewedArticlesActivity extends AppCompatActivity implements MostViewedArticlesContract.View {

    private RecyclerView rv;
    private ProgressBar progressBar;
    private MostViewedArticleAdapter adapter;
    private MostViewedArticlesContract.UserActionsListener presenter;

    private List<Results> results = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.most_viewed);
        initView();
        presenter.getMostViewedArticles();;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_main) {
            Intent intent = new Intent(this, SearchArticlesActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    private void initView(){
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        setTitle(getString(R.string.most_viewed));
        presenter = new MostViewedArticlesPresenter(this);
        rv = (RecyclerView)findViewById(R.id.recyclerView);
        rv.addItemDecoration(new SimpleDividerItemDecoration(this));
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        adapter = new MostViewedArticleAdapter(this,results);
        rv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

    }


    @Override
    public void setProgressIndicator(boolean active) {
        if(active){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadMostViewedArticles(MostViewedArticleResponse response) {
        if(response !=null){
            adapter.notifyData(response.getResults());
        }
    }

    @Override
    public void failedToFetchArticles() {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, getString(R.string.failure_message), Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
