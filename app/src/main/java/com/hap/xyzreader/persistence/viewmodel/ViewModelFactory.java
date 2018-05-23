package com.hap.xyzreader.persistence.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.hap.xyzreader.network.service.XYZReaderService;
import com.hap.xyzreader.persistence.source.ArticleDataSourceImpl;

/**
 * Created by luis on 5/11/18.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final ArticleDataSourceImpl articleDataSourceImpl;
    private final XYZReaderService xyzReaderService;

    public ViewModelFactory(ArticleDataSourceImpl articleDataSourceImpl, XYZReaderService xyzReaderService) {
        this.articleDataSourceImpl = articleDataSourceImpl;
        this.xyzReaderService = xyzReaderService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArticleViewModel.class)) {
            return (T) new ArticleViewModel(articleDataSourceImpl, xyzReaderService);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
