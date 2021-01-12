package com.xxf.view.ratingbar;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * Author: 炫神
 * Date: 1/12/21 12:53 PM
 * Description:控制渲染进度的drawable
 */
public class ProgressDrawable extends BaseDrawable {

    private Drawable mDrawable;
    private float progress = 0;
    private int progressColor = Color.TRANSPARENT;

    public ProgressDrawable(Drawable drawable) {
        mDrawable = drawable;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public ProgressDrawable setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        invalidateSelf();
        return this;
    }

    /**
     * @param progress 0-1
     */
    public ProgressDrawable setProgress(float progress) {
        this.progress = progress;
        if (this.progress > 1) {
            this.progress = 1;
        }
        invalidateSelf();
        return this;
    }

    @NonNull
    @Override
    public Drawable mutate() {
        mDrawable = mDrawable.mutate();
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas, int width, int height) {

        mDrawable.setAlpha(mAlpha);
        ColorFilter colorFilter = getColorFilterForDrawing();
        if (colorFilter != null) {
            mDrawable.setColorFilter(colorFilter);
        }

        int tileHeight = mDrawable.getIntrinsicHeight();
        float scale = (float) height / tileHeight;
        canvas.scale(scale, scale);
        
        int tileWidth = mDrawable.getIntrinsicWidth();
        mDrawable.setBounds(0, 0, tileWidth, tileHeight);
        mDrawable.draw(canvas);

        if (progress >= 0 && progress <= 1) {
            Drawable mutate = mDrawable.mutate();
            DrawableCompat.setTint(mutate, progressColor);
            canvas.drawBitmap(drawableToBitmap(mutate),
                    new Rect(0, 0, (int) (tileWidth * progress), tileHeight),
                    new Rect(0, 0, (int) (tileWidth * progress), tileHeight), null);
        }
    }


    static final Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
