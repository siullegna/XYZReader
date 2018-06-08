package com.hap.xyzreader;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.hap.xyzreader.persistence.viewmodel.ArticleViewModel;
import com.hap.xyzreader.persistence.viewmodel.ViewModelFactory;
import com.hap.xyzreader.widget.EmptyScreenView;
import com.hap.xyzreader.widget.ErrorScreenView;

import javax.inject.Inject;

/**
 * Created by luis on 5/8/18.
 */
public abstract class BaseAppActivity extends AppCompatActivity implements AppInterface {
    private Snackbar snackbar;
    private CoordinatorLayout root;
    @Nullable
    protected Toolbar toolbar;
    @Nullable
    private ProgressBar loader;
    @Nullable
    private EmptyScreenView emptyScreenView;
    @Nullable
    private ErrorScreenView errorScreenView;

    @Inject
    protected ViewModelFactory viewModelFactory;
    protected ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        XYZReaderApplication.getInstance().getXyzReaderAppComponent().inject(this);

        articleViewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel.class);

        root = findViewById(R.id.root);
        toolbar = findViewById(R.id.toolbar);
        loader = findViewById(R.id.loader);
        emptyScreenView = findViewById(R.id.empty_screen_view);
        errorScreenView = findViewById(R.id.error_screen_view);

        setSupportActionBar(toolbar);
    }

    @Override
    public void showLoader() {
        if (loader != null) {
            loader.setVisibility(View.VISIBLE);
        }
        hideEmptyScreen();
        hideError();
        hideContent();
    }

    @Override
    public void hideLoader() {
        if (loader != null) {
            loader.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        if (errorScreenView != null) {
            errorScreenView.setVisibility(View.VISIBLE);
        }
        hideContent();
        hideEmptyScreen();
        hideLoader();
    }

    @Override
    public void hideError() {
        if (errorScreenView != null) {
            errorScreenView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmptyScreen() {
        if (emptyScreenView != null) {
            emptyScreenView.setVisibility(View.VISIBLE);
        }
        hideLoader();
        hideError();
        hideContent();
    }

    @Override
    public void hideEmptyScreen() {
        if (emptyScreenView != null) {
            emptyScreenView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSnackBar(String message) {
        if (snackbar != null) {
            snackbar.dismiss();
        }

        snackbar = Snackbar.make(root, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
