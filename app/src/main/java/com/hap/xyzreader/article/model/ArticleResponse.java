package com.hap.xyzreader.article.model;

import com.hap.xyzreader.persistence.entity.ArticleEntity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by luis on 5/11/18.
 */

public class ArticleResponse {
    @Nullable
    private ArrayList<ArticleEntity> articleEntity;

    @Nullable
    private Throwable error;

    @Nullable
    public Source source;

    @Nullable
    public ArrayList<ArticleEntity> getArticleEntity() {
        return articleEntity;
    }

    public void setArticleEntity(@Nullable ArrayList<ArticleEntity> articleEntity) {
        this.articleEntity = articleEntity;
    }

    public void setArticleEntity(@Nullable List<ArticleEntity> articleEntity) {
        this.articleEntity = new ArrayList<>();
        if (articleEntity != null) {
            this.articleEntity.addAll(articleEntity);
        }
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    public void setError(@Nullable Throwable error) {
        this.error = error;
    }

    @Nullable
    public Source getSource() {
        return source;
    }

    public void setSource(@Nullable Source source) {
        this.source = source;
    }

    public enum Source {
        NETWORK,
        LOCAL_DB
    }
}
