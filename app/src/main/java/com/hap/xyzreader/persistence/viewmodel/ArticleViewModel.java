package com.hap.xyzreader.persistence.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hap.xyzreader.XYZReaderApplication;
import com.hap.xyzreader.article.adapter.ArticleAdapter;
import com.hap.xyzreader.article.model.ArticleResponse;
import com.hap.xyzreader.network.service.XYZReaderService;
import com.hap.xyzreader.persistence.entity.ArticleEntity;
import com.hap.xyzreader.persistence.model.XYZReaderResponse;
import com.hap.xyzreader.persistence.source.ArticleDataSource;
import com.hap.xyzreader.util.LoadingState;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luis on 5/11/18.
 */

public class ArticleViewModel extends ViewModel implements ArticleViewInterface {
    private final ArticleAdapter articleAdapter = new ArticleAdapter();
    private final ArticleDataSource articleDataSource;
    private final XYZReaderService xyzReaderService;
    private final MutableLiveData<ArticleResponse> articles = new MutableLiveData<>();
    private LoadingState loadingState = LoadingState.IDLE;

    public MutableLiveData<ArticleResponse> getArticles() {
        return articles;
    }

    public LoadingState getLoadingState() {
        return loadingState;
    }

    public void setLoadingState(LoadingState loadingState) {
        this.loadingState = loadingState;
    }

    public ArticleAdapter getArticleAdapter() {
        return articleAdapter;
    }

    public boolean isEmptyArticleList() {
        return articleAdapter.isEmpty();
    }

    ArticleViewModel(ArticleDataSource articleDataSource, XYZReaderService xyzReaderService) {
        this.articleDataSource = articleDataSource;
        this.xyzReaderService = xyzReaderService;

        XYZReaderApplication.getInstance().getXyzReaderAppComponent().inject(this);
    }

    @Override
    public LiveData<ArticleResponse> getArticlesList() {
        return xyzReaderService.getArticlesList();
    }

    public void handleResponse(@Nonnull final ArticleResponse articleResponse) {
        if (articleResponse.getError() != null || articleResponse.getArticleEntity() == null) {
            Observable.just(articleDataSource)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<ArticleDataSource>() {
                        @Override
                        public void accept(ArticleDataSource articleDataSource) throws Exception {
                            loadingState = LoadingState.ERROR;
                            final List<ArticleEntity> selectedEntities = articleDataSource.selectAll();
                            articleResponse.setArticleEntity(selectedEntities);
                            notifyArticle(articleResponse);
                        }
                    });
        } else {
            Observable.just(articleDataSource)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<ArticleDataSource>() {
                        @Override
                        public void accept(ArticleDataSource articleDataSource) throws Exception {
                            loadingState = LoadingState.COMPLETE;
                            for (final ArticleEntity articleEntity : articleResponse.getArticleEntity()) {
                                articleDataSource.insertAll(articleEntity);
                            }
                            notifyArticle(articleResponse);
                        }
                    });
        }
    }

    private void notifyArticle(final ArticleResponse articleResponse) {
        Observable.just(articleResponse)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ArticleResponse>() {
                    @Override
                    public void accept(ArticleResponse articleResponse) throws Exception {
                        articles.setValue(articleResponse);
                    }
                });
    }
}
