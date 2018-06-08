package com.hap.xyzreader.persistence.source;

import android.arch.lifecycle.LiveData;

import com.hap.xyzreader.persistence.AppDatabase;
import com.hap.xyzreader.persistence.dao.ArticleDao;
import com.hap.xyzreader.persistence.entity.ArticleEntity;

import java.util.List;

/**
 * Created by luis on 5/11/18.
 */

public class ArticleDataSourceImpl implements ArticleDataSource {
    private final ArticleDao articleDao = AppDatabase.getInstance().articleDao();

    @Override
    public void insertAll(ArticleEntity... articleEntities) {
        articleDao.insertAll(articleEntities);
    }

    @Override
    public List<ArticleEntity> selectAll() {
        return articleDao.selectAll();
    }

    @Override
    public ArticleEntity selectArticleById(int articleId) {
        return articleDao.selectArticleById(articleId);
    }
}
