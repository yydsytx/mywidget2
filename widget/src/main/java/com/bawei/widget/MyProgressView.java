package com.bawei.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyProgressView extends View {
    private Paint paint_hu,paint_text;
    private int progress;//扫描的弧度
    private int num;//自定义属性：数字
    private int hucolor;//自定义属性：圆弧颜色
    private int textcolor;//自定是属性：文字颜色
    public MyProgressView(Context context) {
        super(context);
        initPaint();
    }
    public MyProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyProgressView);
        num = typedArray.getInteger(R.styleable.MyProgressView_num,5);
        hucolor = typedArray.getColor(R.styleable.MyProgressView_hucolor, Color.GRAY);
        textcolor = typedArray.getColor(R.styleable.MyProgressView_textcolor, Color.BLACK);
        typedArray.recycle();


        initPaint();
    }
    private void initPaint() {
        paint_hu = new Paint();
        paint_hu.setAntiAlias(true);//抗锯齿
        paint_hu.setDither(true);//抗抖动
        paint_hu.setColor(hucolor);//颜色
        paint_hu.setStrokeWidth(10);//宽度
        paint_hu.setStyle(Paint.Style.STROKE);//空心


        paint_text = new Paint();
        paint_text.setAntiAlias(true);
        paint_text.setDither(true);
        paint_text.setColor(textcolor);
        paint_text.setTextSize(40);
    }
    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(10,10,250,250);//范围大小
        canvas.drawArc(rectF,0,progress,false,paint_hu);//绘制圆弧
        canvas.drawText(num+"",getMeasuredWidth()/2,getMeasuredHeight()/2,paint_text);
    }


    //结合动画:ValueAnimator
    public void start(){
        //圆弧：0-360
        {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 360);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    progress = (int) animation.getAnimatedValue();
                    invalidate();//重新绘制
                }
            });
            valueAnimator.setDuration(num*1000);
            valueAnimator.start();
        }
        //文字：5-0
        {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(num, 0);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    num = (int) animation.getAnimatedValue();
                    invalidate();//重新绘制
                }
            });
            valueAnimator.setDuration(num*1000);
            valueAnimator.start();
        }
    }




}
