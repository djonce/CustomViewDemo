package com.djonce.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 *  自定义进度条
 *
 * Created by wangj on 2016/1/6
 */
public class CustomProgressBar extends View {

    /**
     * 第一圈的颜色
     */
    private int mFirstColor;

    /**
     * 第二圈的颜色
     */
    private int mSecendColor;

    /**
     * 进度条速速
     */
    private int mSpeed;

    /**
     * 圆形的宽度
     */
    private int mCircleWidth;

    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当前进度
     */
    private int mProgress;

    /**
     * 是否绘制第二圈
     */
    private boolean isNext;

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initAttrs(context, attrs);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar);
        int n = a.getIndexCount();

        for(int i=0; i<n; i++){
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomProgressBar_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecendColor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomProgressBar_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14,
                                    getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = a.getInt(attr, 20);
                    break;
            }
        }

        a.recycle();
        mPaint = new Paint();
        startWork();
    }

    private void startWork() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (!isNext) {
                            isNext = true;
                        } else {
                            isNext = false;
                        }
                    }
                    postInvalidate();

                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth() / 2;            //获取圆心x坐标
        int radius = center - mCircleWidth / 2; // 半径
        mPaint.setStrokeWidth(mCircleWidth);    // 圆环的宽度
        mPaint.setAntiAlias(true);              //消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);    //设置空心

        RectF oval = new RectF(
                         center - radius,
                         center- radius,
                         center + radius,
                         center + radius);  //定义圆弧大小界限
        if(!isNext) {
            // 第一颜色的圈完成后，开始第二圈颜色
            mPaint.setColor(mFirstColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mSecendColor); //
            canvas.drawArc(oval, -90, mProgress, false, mPaint);
        }else {
            mPaint.setColor(mSecendColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mFirstColor);
            canvas.drawArc(oval, -90, mProgress, false, mPaint);
        }
    }
}
