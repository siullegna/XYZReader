package com.hap.xyzreader.dagger.component;

import com.hap.xyzreader.BaseAppActivity;
import com.hap.xyzreader.article.adapter.ArticleAdapter;
import com.hap.xyzreader.article.ui.ArticleDecoration;
import com.hap.xyzreader.persistence.viewmodel.ArticleViewModel;

/**
 * Created by luis on 5/8/18.
 */

public interface AppGraph {
    void inject(BaseAppActivity baseAppActivity);

    void inject(ArticleViewModel articleViewModel);

    void inject(ArticleAdapter articleAdapter);

    void inject(ArticleDecoration articleDecoration);
}