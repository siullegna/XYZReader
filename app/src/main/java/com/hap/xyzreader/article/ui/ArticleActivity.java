package com.hap.xyzreader.article.ui;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hap.xyzreader.BaseAppActivity;
import com.hap.xyzreader.R;
import com.hap.xyzreader.article.model.ArticleResponse;
import com.hap.xyzreader.util.DeviceInfo;
import com.hap.xyzreader.util.LoadingState;

public class ArticleActivity extends BaseAppActivity {
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rvArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_article);
        super.onCreate(savedInstanceState);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        rvArticle = findViewById(R.id.rv_article);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        showLoader();

        final int screenWidth = DeviceInfo.getScreenWidth(this);
        final int columns = getResources().getInteger(R.integer.column_num);
        final int marginDiff = getResources().getDimensionPixelSize(R.dimen.article_grid_margin_diff);
        final int headerSize = (screenWidth - marginDiff) / columns;

        articleViewModel.getArticleAdapter().setHeaderSize(headerSize);

        articleViewModel.getArticles()
                .observe(this, new Observer<ArticleResponse>() {
            @Override
            public void onChanged(@Nullable ArticleResponse articleResponse) {
                if (articleResponse == null || articleResponse.getArticleEntity() == null) {
                    showError();
                } else {
                    if (!articleResponse.getArticleEntity().isEmpty()) {
                        if (articleViewModel.getLoadingState() == LoadingState.ERROR) {
                            showSnackBar(getString(R.string.error_snack_bar));
                        }

                        articleViewModel.getArticleAdapter().addAll(articleResponse.getArticleEntity());
                        showContent();
                    } else if (articleViewModel.getLoadingState() == LoadingState.ERROR) {
                        showError();
                    } else {
                        showEmptyScreen();
                    }
                }
            }
        });

        loadArticles();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (articleViewModel.isEmptyArticleList()) {
                    hideEmptyScreen();
                    showLoader();
                } else if (articleViewModel.getLoadingState() == LoadingState.ERROR) {
                    hideError();
                    showLoader();
                }

                loadArticles();
            }
        });

        rvArticle.addItemDecoration(new ArticleDecoration());
        rvArticle.setHasFixedSize(true);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columns);
        rvArticle.setLayoutManager(gridLayoutManager);
        rvArticle.setAdapter(articleViewModel.getArticleAdapter());
    }

    @Override
    public void showError() {
        super.showError();

        if (swipeRefresh != null) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showEmptyScreen() {
        super.showEmptyScreen();

        if (swipeRefresh != null) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showContent() {
        hideEmptyScreen();
        hideError();
        hideLoader();
        if (rvArticle != null) {
            rvArticle.setVisibility(View.VISIBLE);
        }

        if (swipeRefresh != null) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void hideContent() {
        if (rvArticle != null) {
            rvArticle.setVisibility(View.GONE);
        }
    }

    private void loadArticles() {
        if (articleViewModel.getLoadingState() == LoadingState.LOADING) {
            return;
        }

        articleViewModel.setLoadingState(LoadingState.LOADING);

        articleViewModel.getArticlesList()
                .observe(this, new Observer<ArticleResponse>() {
                    @Override
                    public void onChanged(@Nullable ArticleResponse articleResponse) {
                        if (articleResponse != null) {
                            articleViewModel.handleResponse(articleResponse);
                        } else if (articleViewModel.isEmptyArticleList()) {
                            showError();
                        } else {
                            showSnackBar(getString(R.string.error_snack_bar));
                        }
                    }
                });
    }
}