package com.hap.xyzreader.detail.holder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.View;

import com.hap.xyzreader.R;
import com.hap.xyzreader.persistence.converter.DateConverter;
import com.hap.xyzreader.widget.PhotoDraweeView;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by luis on 6/7/18.
 */

public class PhotoHolder extends RecyclerView.ViewHolder {
    private GregorianCalendar START_OF_EPOCH = new GregorianCalendar(2, 1, 1);

    private final PhotoDraweeView photoDraweeView;
    private final AppCompatTextView articleTitle;
    private final AppCompatTextView articleByLine;

    public PhotoHolder(View itemView) {
        super(itemView);
        photoDraweeView = itemView.findViewById(R.id.photo_drawee_view);
        articleTitle = itemView.findViewById(R.id.article_title);
        articleByLine = itemView.findViewById(R.id.article_byline);
    }

    public void setupView(String photo, String title, Date date, String author) {
        photoDraweeView.setupImage(photo, R.drawable.ic_insert_photo_white_24, null);

        articleTitle.setText(title);

        if (!date.before(START_OF_EPOCH.getTime())) {
            articleByLine.setText(Html.fromHtml(
                    DateUtils.getRelativeTimeSpanString(
                            date.getTime(),
                            System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                            DateUtils.FORMAT_ABBREV_ALL).toString()
                            + " by <font color='#ffffff'>"
                            + author
                            + "</font>"));
        } else {
            // If date is before 1902, just show the string
            articleByLine.setText(Html.fromHtml(
                    DateConverter.fromDateToString(date) + " by <font color='#ffffff'>"
                            + author
                            + "</font>"));

        }
    }
}
