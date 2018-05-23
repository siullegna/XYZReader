package com.hap.xyzreader.article.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hap.xyzreader.R;
import com.hap.xyzreader.XYZReaderApplication;
import com.hap.xyzreader.article.holder.ArticleHolder;
import com.hap.xyzreader.persistence.entity.ArticleEntity;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by luis on 5/11/18.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {
    private final ArrayList<ArticleEntity> articleEntities = new ArrayList<>();
    @Inject
    protected Context context;
    private int headerSize = -1;

    public void setHeaderSize(int headerSize) {
        this.headerSize = headerSize;
    }

    public ArticleAdapter() {
        XYZReaderApplication.getInstance().getXyzReaderAppComponent().inject(this);
    }

    public boolean isEmpty() {
        return articleEntities.isEmpty();
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.article_holder, parent, false);
        return new ArticleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        final ArticleEntity articleEntity = getArticleByPosition(position);
        if (headerSize <= -1) {
            throw new IllegalArgumentException("You must provide a {headerSize} > 0");
        }
        holder.setupView(articleEntity, headerSize);
    }

    @Override
    public int getItemCount() {
        return articleEntities.size();
    }

    private ArticleEntity getArticleByPosition(final int position) {
        return articleEntities.get(position);
    }

    public void addAll(final ArrayList<ArticleEntity> articleEntities) {
        this.articleEntities.clear();
        this.articleEntities.addAll(articleEntities);
        notifyDataSetChanged();
    }
}