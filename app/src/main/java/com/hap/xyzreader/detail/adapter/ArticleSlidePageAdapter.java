package com.hap.xyzreader.detail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hap.xyzreader.detail.ui.ArticleDetailFragment;

import java.util.ArrayList;

/**
 * Created by luis on 6/6/18.
 */

public class ArticleSlidePageAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<Integer> articleIdsList;

    private int getArticlePositionById(final int position) {
        return articleIdsList.get(position);
    }

    public ArticleSlidePageAdapter(final FragmentManager fm, final ArrayList<Integer> articleIdsList) {
        super(fm);
        this.articleIdsList = articleIdsList;
    }

    @Override
    public Fragment getItem(int position) {
        final int articleId = getArticlePositionById(position);
        return ArticleDetailFragment.getInstance(articleId);
    }

    @Override
    public int getCount() {
        return articleIdsList.size();
    }
}
