package com.hap.xyzreader.persistence.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luis on 5/8/18.
 */
public class XYZReaderResponse {
    private int id;
    private String title;
    private String author;
    private String body;
    private String thumb;
    private String photo;
    @SerializedName("aspect_ratio")
    private float aspectRatio;
    @SerializedName("published_date")
    private String publishedDate;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getThumb() {
        return thumb;
    }

    public String getPhoto() {
        return photo;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public String getPublishedDate() {
        return publishedDate;
    }
}