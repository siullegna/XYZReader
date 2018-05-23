package com.hap.xyzreader;

/**
 * Created by luis on 5/11/18.
 */

public interface AppInterface {
    void showLoader();

    void hideLoader();

    void showError();

    void hideError();

    void showEmptyScreen();

    void hideEmptyScreen();

    void showContent();

    void hideContent();

    void showSnackBar(final String message);
}
