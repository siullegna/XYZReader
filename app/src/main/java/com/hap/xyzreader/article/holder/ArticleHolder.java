package com.hap.xyzreader.article.holder;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hap.xyzreader.R;
import com.hap.xyzreader.persistence.converter.DateConverter;
import com.hap.xyzreader.persistence.entity.ArticleEntity;
import com.hap.xyzreader.widget.PhotoDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis on 5/11/18.
 */

public class ArticleHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.article_header)
    View articleHeader;
    @BindView(R.id.article_thumbnail)
    PhotoDraweeView articleThumbnail;
    @BindView(R.id.article_loader)
    ProgressBar articleLoader;
    @BindView(R.id.article_title)
    AppCompatTextView articleTitle;
    @BindView(R.id.article_date)
    AppCompatTextView articleDate;
    @BindView(R.id.article_author)
    AppCompatTextView articleAuthor;
    private boolean isPhotoLoaded = false;
    private final Context context;

    public ArticleHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public void setupView(final ArticleEntity articleEntity, final int headerSize) {
        if (isPhotoLoaded) {
            articleLoader.setVisibility(View.GONE);
        } else {
            articleLoader.setVisibility(View.VISIBLE);
        }
        final LinearLayout.LayoutParams lpHeader = (LinearLayout.LayoutParams) articleHeader.getLayoutParams();
        lpHeader.height = headerSize;
        lpHeader.width = headerSize;
        articleHeader.requestLayout();

        // we setup the photo
        articleThumbnail.setupImage(articleEntity.getThumb(), R.drawable.ic_insert_photo_white_24, new PhotoDraweeView.PhotoLoadListener() {
            @Override
            public void onSuccess() {
                isPhotoLoaded = true;
                if (articleLoader != null) {
                    articleLoader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure() {
                isPhotoLoaded = false;
                if (articleLoader != null) {
                    articleLoader.setVisibility(View.GONE);
                }
            }
        });

        // we setup the information about the article
        articleTitle.setText(articleEntity.getTitle());
        articleDate.setText(DateConverter.fromDate(articleEntity.getPublishedDate()));
        articleAuthor.setText(context.getString(R.string.article_author, articleEntity.getAuthor()));
    }
}