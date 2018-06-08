package com.hap.xyzreader.detail.ui;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hap.xyzreader.BaseAppFragment;
import com.hap.xyzreader.R;
import com.hap.xyzreader.detail.adapter.BodyAdapter;
import com.hap.xyzreader.detail.model.BodyItem;
import com.hap.xyzreader.detail.model.BodyType;
import com.hap.xyzreader.persistence.converter.DateConverter;
import com.hap.xyzreader.persistence.entity.ArticleEntity;
import com.hap.xyzreader.widget.PhotoDraweeView;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luis on 6/6/18.
 */

public class ArticleDetailFragment extends BaseAppFragment {
    private static final String ARG_ARTICLE_ID_KEY = "com.hap.xyzreader.detail.ui.ARG_ARTICLE_ID_KEY";
    private ProgressBar loader;
    private BodyAdapter bodyAdapter;

    private int articleId;

    public static ArticleDetailFragment getInstance(final int articleId) {
        final ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ARTICLE_ID_KEY, articleId);
        articleDetailFragment.setArguments(args);
        return articleDetailFragment;
    }

    private ArrayList<BodyItem> getBodyItemList(ArticleEntity articleEntity, final ArrayList<String> bodies) {
        final ArrayList<BodyItem> bodyItemList = new ArrayList<>();

        // create the header
        final BodyItem header = new BodyItem(BodyType.PHOTO_HEADER, articleEntity.getPhoto(), articleEntity.getTitle(), articleEntity.getPublishedDate(),
                articleEntity.getAuthor());

        bodyItemList.add(header);

        for (int i = 0; i < bodies.size(); i++) {
            final BodyItem message = new BodyItem(BodyType.MESSAGE, bodies.get(i));
            bodyItemList.add(message);
        }

        return bodyItemList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            articleId = getArguments().getInt(ARG_ARTICLE_ID_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        loader = view.findViewById(R.id.loader);
        final RecyclerView rvBody = view.findViewById(R.id.rv_body);
        final FloatingActionButton shareFab = view.findViewById(R.id.share_fab);

        shareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(getActivity())
                            .setType("text/plain")
                            .setText(getString(R.string.share_article))
                            .getIntent(), getString(R.string.action_share)));
                }
            }
        });

        bodyAdapter = new BodyAdapter();

        rvBody.setHasFixedSize(false);
        rvBody.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBody.setAdapter(bodyAdapter);

        articleViewModel.getArticle().observe(this, new Observer<ArticleEntity>() {
            @Override
            public void onChanged(@Nullable final ArticleEntity articleEntity) {
                if (articleEntity != null) {
                    Observable.just(articleEntity.getBody())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String body) throws Exception {
                                    final ArrayList<String> bodies = new ArrayList<>();
                                    bodies.addAll(Arrays.asList(body.split("\n")));
                                    bodies.add(getString(R.string.body_end_empty));

                                    final ArrayList<BodyItem> bodyItemList = getBodyItemList(articleEntity, bodies);
                                    if (bodyAdapter != null) {
                                        bodyAdapter.addAll(bodyItemList);
                                    }
                                    if (loader != null) {
                                        loader.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
            }
        });

        articleViewModel.selectArticleById(articleId);

        return view;
    }
}
