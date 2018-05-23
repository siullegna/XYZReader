package com.hap.xyzreader.persistence.viewmodel;

import android.arch.lifecycle.LiveData;

import com.hap.xyzreader.article.model.ArticleResponse;

/**
 * Created by luis on 5/22/18.
 */

public interface ArticleViewInterface {
    LiveData<ArticleResponse> getArticlesList();
}
