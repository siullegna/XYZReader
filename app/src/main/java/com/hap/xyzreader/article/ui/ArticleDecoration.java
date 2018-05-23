package com.hap.xyzreader.article.ui;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hap.xyzreader.R;
import com.hap.xyzreader.XYZReaderApplication;

import javax.inject.Inject;

/**
 * Created by luis on 5/12/18.
 */

public class ArticleDecoration extends RecyclerView.ItemDecoration {
    private final int space;
    @Inject
    protected Resources resources;

    ArticleDecoration() {
        XYZReaderApplication.getInstance().getXyzReaderAppComponent().inject(this);

        space = resources.getDimensionPixelSize(R.dimen.article_grid_item_margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.top = space;
        outRect.left = space;
        outRect.bottom = space;
        outRect.right = space;
    }
}
