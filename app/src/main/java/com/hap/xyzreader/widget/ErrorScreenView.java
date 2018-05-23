package com.hap.xyzreader.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.hap.xyzreader.R;

/**
 * Created by luis on 5/21/18.
 */

public class ErrorScreenView extends LinearLayout {
    private final PhotoDraweeView photoDraweeView;

    public ErrorScreenView(Context context) {
        this(context, null);
    }

    public ErrorScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrorScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.error_screen_view, this);

        photoDraweeView = findViewById(R.id.error_icon);

        photoDraweeView.setupImage(R.drawable.ic_could_error);
    }
}
