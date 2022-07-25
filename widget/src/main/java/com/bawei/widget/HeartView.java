package com.bawei.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HeartView extends RelativeLayout {
    public HeartView(Context context) {
        super(context);
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);


    }
    //触摸 ：DOWN 按下 MOVE 移动 UP 抬起
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();//事件类型:DOWN 按下 MOVE 移动 UP 抬起
//        float x = event.getX();//事件发生的地点X
//        float y = event.getY();//事件发生的地点Y


        if(event.getAction() == MotionEvent.ACTION_DOWN){//按下
            //创建imageView
            final ImageView imageView = new ImageView(getContext());
            int random = (int) (Math.random() * 4);

                imageView.setImageResource(R.drawable.lovee);//设置图标

            LayoutParams layoutParams = new LayoutParams(100, 100);//图片的大小
            layoutParams.leftMargin = (int) event.getX()-100;//图片的位置
            layoutParams.topMargin = (int) event.getY()-100;//图片的位置
            imageView.setLayoutParams(layoutParams);//设置布局
            //为ImageView设置动画

            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 1, 0);
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1, 2);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1, 2);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY", 0, -200);
            ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView, "rotation", 0, 30);
            rotation.setInterpolator(new CycleInterpolator(5));//周期插值器 左右摇摆5次
            animatorSet.setDuration(1000)
                    .play(alpha)
                    .with(scaleX)
                    .with(scaleY)
                    .with(translationY)
                    .with(rotation);
            animatorSet.start();

            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    removeView(imageView);
                }
            });

            //addView向容器中添加ImageView
            addView(imageView);


        }
        return true;
    }
}
