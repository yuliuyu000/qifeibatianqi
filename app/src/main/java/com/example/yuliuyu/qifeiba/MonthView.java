package com.example.yuliuyu.qifeiba;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/7/21 11:10
 * 修改人：yuliuyu
 * 修改时间：2017/7/21 11:10
 * 修改备注：
 */
public class MonthView extends View {
    int mWidth, mHeight;

    Paint mSolarPaint;
Paint mSorlarPaint;

    public MonthView(Context context) {
        super(context);
        mSolarPaint = initPaint(Color.RED, 20);
    }


    public MonthView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mSolarPaint = initPaint(Color.WHITE, 10);
        mSorlarPaint=initPaint(Color.RED, 20);
    }


    public MonthView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSolarPaint = initPaint(Color.RED, 20);
    }


    private Paint initPaint(int color, float paintSize) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setTextSize(paintSize);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth=getWidth();
        mHeight=getHeight();
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                Rect mRect =new Rect(mWidth/7*j,mHeight/6*i,mWidth/7*j+mWidth/7,mHeight/6*i+mHeight/6);
                canvas.drawRect(mRect,mSolarPaint);
                Paint.FontMetricsInt fontMetrics = mSorlarPaint.getFontMetricsInt();
                int baseline = (mRect.bottom + mRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                //canvas.drawCircle(mRect.centerX(), mRect.centerY(), 5, mSorlarPaint);
                //canvas.drawText();
                canvas.drawText(i*j + "", mRect.centerX(), baseline, mSorlarPaint);
            }
        }





    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }
}
