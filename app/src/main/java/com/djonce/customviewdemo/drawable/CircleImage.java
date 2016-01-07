package com.djonce.customviewdemo.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 *
 * Created by djonce@qq.com on 2016/1/7 0007.
 */
public class CircleImage extends Drawable {

    private Paint mPaint;
    private int mWidth;
    private Bitmap mBitmap;

    public CircleImage(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        BitmapShader bitmapShader = new BitmapShader(
                mBitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);

        mWidth = Math.min(mBitmap.getWidth(), mBitmap.getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        int raduis = mWidth / 2;
        canvas.drawCircle(raduis, raduis, raduis, mPaint);
    }

    @Override
    public int getIntrinsicHeight() {
        return mWidth;
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
