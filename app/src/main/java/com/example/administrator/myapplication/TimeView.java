package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/1/11.
 */

public class TimeView extends View {
    Paint paint;
    Context context;
    private float secondRadiu;
    private float minRadiu;
    private float hourRadiu;

    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (secondRadiu == 360) {
                secondRadiu = 0;
            }
            if (minRadiu == 360) {
                minRadiu = 0;

            }
            if (hourRadiu == 360) {
                hourRadiu = 0;
            }
            minRadiu = minRadiu + 0.1f;
            hourRadiu = hourRadiu + 1f / 240;
            secondRadiu = secondRadiu + 6;
            postInvalidate();
        }
    };

    public void setTime(int second, int min, int hour) {
        minRadiu = (min + second * 1.0f / 60f) * 6f;
        hourRadiu = (hour + min * 1.0f / 60f + second * 1.0f / 3600f) * 30f;
        secondRadiu = second * 6f;
        invalidate();
    }

    public void start() {
        timer.schedule(timerTask, 0, 1000);
    }

    public TimeView(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
    }

    public void initPaint() {
        paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#cdcdcd"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆框
        paint.setColor(Color.parseColor("#cdcdcd"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 3, paint);
        //圆点
        canvas.drawPoint(getWidth() / 2, getHeight() / 2, paint);
        //刻度线
        paint.setColor(Color.parseColor("#000000"));
        paint.setStrokeWidth(1);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        for (int i = 0; i < 360; i++) {
            if (i % 30 == 0) {//长刻度
                canvas.drawLine(getWidth() / 3 - 25, 0, getWidth() / 3, 0, paint);
            } else if (i % 6 == 0) {//中刻度*/
                canvas.drawLine(getWidth() / 3 - 14, 0, getWidth() / 3, 0, paint);
            } else {//短刻度
                canvas.drawLine(getWidth() / 3 - 9, 0, getWidth() / 3, 0, paint);
            }

            canvas.rotate(1);


        }

        paint.setTextSize(25);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 12; i++) {
            if (i == 0) {
                drawNum(canvas, i * 30, 12 + "", paint);
            } else {
                drawNum(canvas, i * 30, i + "", paint);
            }
        }
        //秒针
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.rotate(secondRadiu);
        canvas.drawLine(0, 0, 0, -210, paint);
        canvas.restore();
        //分针
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setColor(Color.BLUE);
        canvas.rotate(minRadiu);
        canvas.drawLine(0, 0, 0, -150, paint);
        canvas.restore();
        //时针
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.GREEN);
        canvas.rotate(hourRadiu);
        canvas.drawLine(0, 0, 0, -110, paint);
        canvas.restore();


    }

    private void drawNum(Canvas canvas, int degree, String text, Paint paint) {
        Rect textBound = new Rect();//创建一个矩形
        paint.getTextBounds(text, 0, text.length(), textBound);
        canvas.rotate(degree);
        canvas.translate(0, 50 - getWidth() / 3);
        canvas.rotate(-degree);
        canvas.drawText(text, -textBound.width() / 2,
                textBound.height() / 2, paint);
        canvas.rotate(degree);
        canvas.translate(0, getWidth() / 3 - 50);
        canvas.rotate(-degree);
    }

}
