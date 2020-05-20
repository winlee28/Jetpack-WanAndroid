package com.win.lib_image_loader.app;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.Target;
import com.win.lib_image_loader.R;
import com.win.lib_image_loader.image.CustomRequestListener;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;

/**
 * 图处加载类，外界唯一调用类,直持为view,notifaication,appwidget加载图片
 */
public class ImageLoaderManager {

    private ImageLoaderManager() {
    }

    public static ImageLoaderManager getInstance() {
        return ImageLoaderManager.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final ImageLoaderManager instance = new ImageLoaderManager();
    }

    /**
     * 为notification加载图
     *
     * @param context
     * @param rv
     * @param id
     * @param notification
     * @param NOTIFICATION_ID
     * @param url
     */
    public void displayImageForNotification(Context context, RemoteViews rv, int id,
                                            Notification notification, int NOTIFICATION_ID, String url) {
        this.displayImageForTarget(context,
                initNotificationTarget(context, id, rv, notification, NOTIFICATION_ID), url);
    }

    /**
     * 不带回调的加载
     *
     * @param imageView
     * @param url
     */
    public void displayImageForView(ImageView imageView, String url) {
        this.displayImageForView(imageView, url, null);
    }

    /**
     * 带回调的加载图片方法
     *
     * @param imageView
     * @param url
     * @param requestListener
     */
    public void displayImageForView(ImageView imageView, String url,
                                    CustomRequestListener requestListener) {

        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
//                .apply(initCommonRequestOption())
//                .transition(withCrossFade())
                .into(imageView);
    }

    /**
     * 带回调的加载圆形图片方法
     *
     * @param imageView
     * @param url
     */
    public void displayImageForCircle(final ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(final Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * 为非view加载图片
     */
    private void displayImageForTarget(Context context, Target target, String url) {
        this.displayImageForTarget(context, target, url, null);
    }

    /**
     * 为非view加载图片
     */
    private void displayImageForTarget(Context context, Target target, String url,
                                       CustomRequestListener requestListener) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transition(withCrossFade())
                .fitCenter()
                .listener(requestListener)
                .into(target);
    }

    /*
     * 初始化Notification Target
     */
    private NotificationTarget initNotificationTarget(Context context, int id, RemoteViews rv,
                                                      Notification notification, int NOTIFICATION_ID) {
        NotificationTarget notificationTarget =
                new NotificationTarget(context, id, rv, notification, NOTIFICATION_ID);
        return notificationTarget;
    }

    @SuppressLint("CheckResult")
    private RequestOptions initCommonRequestOption() {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(false)
                .priority(Priority.NORMAL);
        return options;
    }
}
