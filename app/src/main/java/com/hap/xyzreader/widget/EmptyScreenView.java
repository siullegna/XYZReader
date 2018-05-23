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

public class EmptyScreenView extends LinearLayout {
    private final PhotoDraweeView photoDraweeView;

    public EmptyScreenView(Context context) {
        this(context, null);
    }

    public EmptyScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.empty_screen_view, this);

        photoDraweeView = findViewById(R.id.empty_icon);

        photoDraweeView.setupImage(R.drawable.ic_empty_list);
    }
}
