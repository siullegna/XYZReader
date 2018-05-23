package com.hap.xyzreader.network.service;

import android.arch.lifecycle.LiveData;

import com.hap.xyzreader.article.model.ArticleResponse;

/**
 * Created by luis on 5/22/18.
 */

public interface XYZReaderServiceInterface {
    LiveData<ArticleResponse> getArticlesList();
}
