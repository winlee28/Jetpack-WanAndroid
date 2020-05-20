package com.win.lib_image_loader.image;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public interface CustomRequestListener extends RequestListener {
    @Override
    boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource);

    @Override
    boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource);
}
