package com.hap.xyzreader.detail.model;

import java.util.Date;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by luis on 6/7/18.
 */

public class BodyItem {
    @Nonnull
    private final BodyType bodyType;
    @Nullable
    private final String photo;
    @Nullable
    private final String title;
    @Nullable
    private final Date date;
    @Nullable
    private final String author;
    @Nullable
    private final String message;

    private BodyItem(@Nonnull BodyType bodyType, @Nullable String photo, @Nullable String title, @Nullable Date date, @Nullable String author,
                     @Nullable String message) {
        this.bodyType = bodyType;
        this.photo = photo;
        this.title = title;
        this.date = date;
        this.author = author;
        this.message = message;
    }

    public BodyItem(@Nonnull BodyType bodyType, @Nonnull String photo, @Nonnull String title, @Nonnull Date date, @Nonnull String author) {
        this(bodyType, photo, title, date, author, null);
    }

    public BodyItem(@Nonnull BodyType bodyType, @Nonnull String message) {
        this(bodyType, null, null, null, null, message);
    }

    @Nonnull
    public BodyType getBodyType() {
        return bodyType;
    }

    @Nullable
    public String getPhoto() {
        return photo;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public Date getDate() {
        return date;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    @Nullable
    public String getMessage() {
        return message;
    }
}
