package com.hap.xyzreader.persistence.converter;

import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by luis on 5/8/18.
 */
public class DateConverter {
    private static final DateFormat fromFormat = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault());
    private static final DateFormat toFormat = new SimpleDateFormat("MMM dd, YYYY", Locale.getDefault());

    @TypeConverter
    public static Date fromString(final String publishedDate) {
        try {
            return fromFormat.parse(publishedDate);
        } catch (ParseException e) {
            return null;
        }
    }

    @TypeConverter
    public static String fromDate(final Date publishedDate) {
        if (publishedDate == null) {
            return null;
        }
        return toFormat.format(publishedDate);
    }
}