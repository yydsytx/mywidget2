package com.bawei.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;


public class SwipeImageView extends AppCompatImageView {
    private float ix,iy;
    private SwipeLister swipeLister;
    private Matrix matrix = new Matrix();
    public void setOnSwipeLister(SwipeLister swipeLister) {
        this.swipeLister = swipeLister;
    }

    public SwipeImageView(Context context) {
        super(context);

    }

    public SwipeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    private float sx,sy;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        if(action == MotionEvent.ACTION_DOWN){
            sx= x;
            sy = y;
        }else if(action == MotionEvent.ACTION_MOVE){
           ix= x-sx;
           if(ix <=-100){//左滑动
               if(swipeLister != null){
                   swipeLister.onLeft();
               }

           }else if(ix>=100){//右滑动
               if(swipeLister != null){
                   swipeLister.onRight();
               }
           }

        }else if(action == MotionEvent.ACTION_UP){
            ix = x-sx;
        }

        Matrix matrix = new Matrix();
        matrix.postTranslate(ix, iy);
//切记修改控件缩放模式为 MATRIX
        setScaleType(ScaleType.MATRIX);
        setImageMatrix(matrix);


        return true;
    }


   public interface  SwipeLister{
        void onLeft();
        void onRight();
    }
}
