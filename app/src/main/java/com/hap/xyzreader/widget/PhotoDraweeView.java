package com.hap.xyzreader.widget;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.hap.xyzreader.R;

import javax.annotation.Nullable;

/**
 * Created by luis on 5/12/18.
 */

public class PhotoDraweeView extends SimpleDraweeView {
    private static final int FADE_DURATION = 300;

    public PhotoDraweeView(Context context) {
        this(context, null);
    }

    public PhotoDraweeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setupImage(final int errorIcon) {
        setupImage(null, errorIcon, null);
    }

    public void setupImage(@Nullable final String photoUrl, final int errorIcon, @Nullable final PhotoLoadListener photoLoadListener) {
        final Uri photoUri = TextUtils.isEmpty(photoUrl) ? Uri.EMPTY : Uri.parse(photoUrl);

        final Drawable failureDrawable = ContextCompat.getDrawable(getContext(), errorIcon);
        if (failureDrawable != null) {
            DrawableCompat.setTint(failureDrawable, ContextCompat.getColor(getContext(), R.color.colorAccent));
        }

        final ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(photoUri)
                .setRotationOptions(RotationOptions.autoRotate())
                .build();

        final AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                        if (photoLoadListener != null) {
                            photoLoadListener.onSuccess();
                        }
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        if (photoLoadListener != null) {
                            photoLoadListener.onFailure();
                        }
                    }
                })
                .setOldController(this.getController())
                .build();

        final GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder
                .newInstance(getResources())
                .setFadeDuration(FADE_DURATION)
                .setFailureImage(failureDrawable)
                .build();

        this.setHierarchy(hierarchy);
        this.setController(controller);
    }

    public interface PhotoLoadListener {
        void onSuccess();

        void onFailure();
    }
}
