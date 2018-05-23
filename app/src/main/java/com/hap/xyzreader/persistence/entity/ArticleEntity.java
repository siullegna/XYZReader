package com.hap.xyzreader.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.hap.xyzreader.persistence.converter.DateConverter;
import com.hap.xyzreader.persistence.model.XYZReaderResponse;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by luis on 5/8/18.
 */
@Entity(tableName = ArticleColumnInfo.TABLE_NAME, indices = {@Index(ArticleColumnInfo._ID)})
public class ArticleEntity {
    @PrimaryKey
    @ColumnInfo(name = ArticleColumnInfo._ID)
    private int id;
    @ColumnInfo(name = ArticleColumnInfo.TITLE)
    private String title;
    @ColumnInfo(name = ArticleColumnInfo.AUTHOR)
    private String author;
    @ColumnInfo(name = ArticleColumnInfo.BODY)
    private String body;
    @ColumnInfo(name = ArticleColumnInfo.THUMB)
    private String thumb;
    @ColumnInfo(name = ArticleColumnInfo.PHOTO)
    private String photo;
    @ColumnInfo(name = ArticleColumnInfo.ASPECT_RATIO)
    private float aspectRatio;
    @ColumnInfo(name = ArticleColumnInfo.PUBLISHED_DATE)
    private Date publishedDate;

    public ArticleEntity() {
    }

    @Ignore
    public ArticleEntity(int id, String title, String author, String body, String thumb, String photo, float aspectRatio, Date publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.thumb = thumb;
        this.photo = photo;
        this.aspectRatio = aspectRatio;
        this.publishedDate = publishedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public static ArticleEntity convert(XYZReaderResponse xyzReaderResponse) {
        final ArticleEntity articleEntity = new ArticleEntity();

        articleEntity.id = xyzReaderResponse.getId();
        articleEntity.title = xyzReaderResponse.getTitle();
        articleEntity.author = xyzReaderResponse.getAuthor();
        articleEntity.body = xyzReaderResponse.getBody();
        articleEntity.thumb = xyzReaderResponse.getThumb();
        articleEntity.photo = xyzReaderResponse.getPhoto();
        articleEntity.aspectRatio = xyzReaderResponse.getAspectRatio();
        articleEntity.publishedDate = DateConverter.fromString(xyzReaderResponse.getPublishedDate());

        return articleEntity;
    }

    public static ArrayList<ArticleEntity> convert(ArrayList<XYZReaderResponse> xyzReaderResponses) {
        final ArrayList<ArticleEntity> xyzReaderEntities = new ArrayList<>();

        for (final XYZReaderResponse xyzReaderResponse : xyzReaderResponses) {
            final ArticleEntity articleEntity = convert(xyzReaderResponse);
            xyzReaderEntities.add(articleEntity);
        }

        return xyzReaderEntities;
    }
}