package com.hap.xyzreader.persistence.entity;

import android.provider.BaseColumns;

/**
 * Created by luis on 5/8/18.
 */
public class ArticleColumnInfo implements BaseColumns {
    public static final String TABLE_NAME = "xyz_reader";

    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String BODY = "body";
    public static final String THUMB = "thumb";
    public static final String PHOTO = "photo";
    public static final String ASPECT_RATIO = "aspect_ratio";
    public static final String PUBLISHED_DATE = "published_date";
}
