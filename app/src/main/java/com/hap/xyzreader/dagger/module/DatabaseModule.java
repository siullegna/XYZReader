package com.hap.xyzreader.dagger.module;

import com.hap.xyzreader.dagger.scope.ApplicationScope;
import com.hap.xyzreader.network.service.XYZReaderService;
import com.hap.xyzreader.persistence.source.ArticleDataSourceImpl;
import com.hap.xyzreader.persistence.viewmodel.ViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luis on 5/8/18.
 */
@Module
public class DatabaseModule {
    @Provides
    @ApplicationScope
    protected ArticleDataSourceImpl provideArticleDataSourceImpl() {
        return new ArticleDataSourceImpl();
    }

    @Provides
    @ApplicationScope
    protected ViewModelFactory provideViewModelFactory(final ArticleDataSourceImpl articleDataSourceImpl, XYZReaderService xyzReaderService) {
        return new ViewModelFactory(articleDataSourceImpl, xyzReaderService);
    }
}