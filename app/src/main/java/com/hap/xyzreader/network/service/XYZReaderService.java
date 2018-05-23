package com.hap.xyzreader.network.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hap.xyzreader.article.model.ArticleResponse;
import com.hap.xyzreader.network.api.XYZReaderApi;
import com.hap.xyzreader.persistence.entity.ArticleEntity;
import com.hap.xyzreader.persistence.model.XYZReaderResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luis on 5/8/18.
 */
public class XYZReaderService implements XYZReaderServiceInterface {
    private final XYZReaderApi xyzReaderApi;

    public XYZReaderService(XYZReaderApi xyzReaderApi) {
        this.xyzReaderApi = xyzReaderApi;
    }

    @Override
    public LiveData<ArticleResponse> getArticlesList() {
        final MutableLiveData<ArticleResponse> liveData = new MutableLiveData<>();
        final ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setSource(ArticleResponse.Source.NETWORK);

        xyzReaderApi.getBooks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ArrayList<XYZReaderResponse>>() {
                    @Override
                    public void accept(ArrayList<XYZReaderResponse> xyzReaderResponses) throws Exception {
                        final ArrayList<ArticleEntity> articleEntities = ArticleEntity.convert(xyzReaderResponses);
                        articleResponse.setArticleEntity(articleEntities);
                        liveData.setValue(articleResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        articleResponse.setError(throwable);
                        liveData.setValue(articleResponse);
                    }
                });

        return liveData;
    }
}
