package com.android.quard.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Describe :贝塞尔曲线学习
 * Created by Knight on 2019/2/1
 * 点滴之行,看世界
 **/
public class PathView extends View {


    //画笔
    private Paint paint;
    //路径
    private Path path;
    //波浪起始左右位置横坐标
    private int waveLength = 400;
    private int origY = 1200;
    //移动的距离
    private int moveDistance;





    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    //重写onDraw方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把路线清除 重新绘制 一定要加上 不然是矩形
        path.reset();
        //设置填充绘制
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //线条宽度
        //paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        int control = waveLength / 2;
        //首先确定初始状态的起点（-400,1200）
        path.moveTo(-waveLength + moveDistance,origY - moveDistance);
        for(int i = -waveLength;i <= getWidth() + waveLength;i += waveLength){
            path.rQuadTo(control / 2,-70,control,0);
            path.rQuadTo(control / 2,70,control,0);
        }
        path.lineTo(getWidth(),getHeight());
        path.lineTo(0,getHeight());
        path.close();
        canvas.drawPath(path, paint);

    }


    private void init() {
        paint = new Paint();
        path = new Path();
    }

    //重置路线的线段
    public void reset() {
        path.reset();

        invalidate();
    }

    /**
     * 动画位移方法
     */
    public void startAnim(){
        //创建动画实例
        ValueAnimator moveAnimator = ValueAnimator.ofInt(0,waveLength);
        //动画的时间
        moveAnimator.setDuration(2500);
        //设置动画次数  INFINITE表示无限循环
        moveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //设置动画插值
        moveAnimator.setInterpolator(new LinearInterpolator());
        //添加监听
        moveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moveDistance = (int)animation.getAnimatedValue();
                invalidate();
            }
        });
        //启动动画
        moveAnimator.start();
    }



}
