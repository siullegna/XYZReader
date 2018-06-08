package com.hap.xyzreader.detail.holder;

import android.content.res.Resources;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hap.xyzreader.R;
import com.hap.xyzreader.XYZReaderApplication;

import javax.inject.Inject;

/**
 * Created by luis on 6/7/18.
 */

public class BodyHolder extends RecyclerView.ViewHolder {
    private final AppCompatTextView body;
    private final String bodyEndEmpty;
    @Inject
    protected Resources resources;

    public BodyHolder(View itemView) {
        super(itemView);
        body = itemView.findViewById(R.id.body);

        XYZReaderApplication.getInstance().getXyzReaderAppComponent().inject(this);

        bodyEndEmpty = resources.getString(R.string.body_end_empty);
    }

    public void setupBody(String body) {
        if (body.equals(bodyEndEmpty)) {
            this.body.setText(body);
        } else {
            this.body.setText(body.trim());
        }
    }
}
