package com.hap.xyzreader;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hap.xyzreader.persistence.viewmodel.ArticleViewModel;
import com.hap.xyzreader.persistence.viewmodel.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created by luis on 6/6/18.
 */

public class BaseAppFragment extends Fragment {
    @Inject
    protected ViewModelFactory viewModelFactory;
    protected ArticleViewModel articleViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        XYZReaderApplication.getInstance().getXyzReaderAppComponent().inject(this);

        articleViewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel.class);
    }
}
