package com.trivagonytimes.ui.Search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.trivagonytimes.R;
import com.trivagonytimes.custom.EndlessRecyclerViewScrollListener;
import com.trivagonytimes.custom.SimpleDividerItemDecoration;
import com.trivagonytimes.model.ApiResponse;
import com.trivagonytimes.model.Doc;
import com.trivagonytimes.ui.Search.adapter.SearchArticleAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchArticlesActivity extends AppCompatActivity implements SearchContract.View{
    private RecyclerView rv;
    private ProgressBar progressBar;
    private TextView searchMsgtext;
    private SearchArticleAdapter adapter;
    private String queryString;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private List<Doc> docs =  new ArrayList<>();
    private SearchContract.UserActionsListener presenter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        setupSearchView(menu);
        return super.onCreateOptionsMenu(menu);
    }




    void loadLayoutManager(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        presenter = new SearchArticlesPresenter(this);
        rv.setLayoutManager(layoutManager);
        rv.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                presenter.loadArticles(queryString,page);
            }
        });

    }


    public  void setupSearchView(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                finish();
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                return true;
            }

        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.clearArticles();
                //mScrollListener.resetState();
                searchMsgtext.setVisibility(View.GONE);
                searchView.clearFocus();
                queryString=query;
                presenter.loadArticles(queryString,0);

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.clearArticles();
                searchMsgtext.setVisibility(View.GONE);
               //mScrollListener.resetState();
                queryString=newText;
                presenter.loadArticles(queryString,0);

                return true;



            }
        });
    }
    private void initView(){
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        setTitle(getString(R.string.article_search));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv = (RecyclerView)findViewById(R.id.recyclerView);
        searchMsgtext = (TextView)findViewById(R.id.searchMsgText);
        rv.addItemDecoration(new SimpleDividerItemDecoration(this));
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        adapter = new SearchArticleAdapter(this,docs);
        rv.setAdapter(adapter);
        loadLayoutManager();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        searchMsgtext.setVisibility(View.GONE);
        if(active){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSearchedArticles(ApiResponse apiResponse) {
        if(apiResponse!=null) {
            searchMsgtext.setVisibility(View.GONE);
            adapter.notifyData(apiResponse.getResponse().getDocs());
        }
    }

    @Override
    public void loadMoreSearchedArticles( ApiResponse apiResponse) {
        if(apiResponse!=null) {
            docs.addAll(apiResponse.getResponse().getDocs());
            int curSize = adapter.getItemCount();
            adapter.notifyItemRangeInserted(curSize, docs.size() - 1);
        }
    }

    @Override
    public void failedToFetchArticles() {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, getString(R.string.failure_message), Snackbar.LENGTH_LONG);

        snackbar.show();
    }


}
