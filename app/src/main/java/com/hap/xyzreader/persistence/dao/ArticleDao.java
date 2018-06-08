package com.hap.xyzreader.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hap.xyzreader.persistence.entity.ArticleColumnInfo;
import com.hap.xyzreader.persistence.entity.ArticleEntity;

import java.util.List;

/**
 * Created by luis on 5/11/18.
 */
@Dao
public interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArticleEntity... articleEntities);

    @Query("SELECT * "
            + "FROM "
            + ArticleColumnInfo.TABLE_NAME)
    List<ArticleEntity> selectAll();

    @Query("SELECT * "
            + "FROM "
            + ArticleColumnInfo.TABLE_NAME
            + " WHERE " + ArticleColumnInfo._ID + " = :articleId")
    ArticleEntity selectArticleById(final int articleId);
}
