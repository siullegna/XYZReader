package com.hap.xyzreader.detail.ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.Slide;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.hap.xyzreader.BaseAppActivity;
import com.hap.xyzreader.R;
import com.hap.xyzreader.detail.adapter.ArticleSlidePageAdapter;

import java.util.ArrayList;

/**
 * Created by luis on 5/22/18.
 */
public class ArticleDetailActivity extends BaseAppActivity {
    public static final String ARG_ARTICLE_ID_LIST_KEY = "com.hap.xyzreader.detail.ui.ARG_ARTICLE_ID_LIST_KEY";
    public static final String ARG_CURRENT_POSITION_KEY = "com.hap.xyzreader.detail.ui.ARG_CURRENT_POSITION_KEY";

    private int currentPage;
    private ArrayList<Integer> articleIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_article_detail);
        super.onCreate(savedInstanceState);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        final ViewPager viewPager = findViewById(R.id.view_pager);

        final FloatingActionButton shareFab = findViewById(R.id.share_fab);

        shareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(ArticleDetailActivity.this)
                        .setType("text/plain")
                        .setText(getString(R.string.share_article))
                        .getIntent(), getString(R.string.action_share)));
            }
        });

        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt(ARG_CURRENT_POSITION_KEY);
            articleIdList = savedInstanceState.getIntegerArrayList(ARG_ARTICLE_ID_LIST_KEY);
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            currentPage = getIntent().getExtras().getInt(ARG_CURRENT_POSITION_KEY);
            articleIdList = getIntent().getExtras().getIntegerArrayList(ARG_ARTICLE_ID_LIST_KEY);
        }

        if (articleIdList != null) {
            final ArticleSlidePageAdapter pageAdapter = new ArticleSlidePageAdapter(getSupportFragmentManager(), articleIdList);
            viewPager.setAdapter(pageAdapter);
            viewPager.setCurrentItem(currentPage);
        } else {
            showError();
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            final Drawable arrow = toolbar.getNavigationIcon();
            if (arrow != null) {
                arrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
                actionBar.setHomeAsUpIndicator(arrow);
            }
        }

        showLoader();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_CURRENT_POSITION_KEY, currentPage);
        outState.putIntegerArrayList(ARG_ARTICLE_ID_LIST_KEY, articleIdList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showContent() {

    }

    @Override
    public void hideContent() {

    }
}
