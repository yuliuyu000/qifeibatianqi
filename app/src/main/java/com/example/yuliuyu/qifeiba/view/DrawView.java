package com.example.yuliuyu.qifeiba.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;

/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/7/31 10:30
 * 修改人：yuliuyu
 * 修改时间：2017/7/31 10:30
 * 修改备注：
 */
public class DrawView extends View{

    Paint mPaint=new Paint();
    public DrawView(Context context) {
        super(context);
    }


    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);

    }
}
