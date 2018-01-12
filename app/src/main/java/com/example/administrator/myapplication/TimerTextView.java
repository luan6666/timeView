package com.example.administrator.myapplication;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 倒计时 textview 拼团购 Created by ldx on 2017/7/18.
 */
public class TimerTextView extends android.support.v7.widget.AppCompatTextView implements Runnable {
	protected long timeMillions;
	long nd = 1000*24*60*60;//一天的毫秒数  
	  long nh = 1000*60*60;//一小时的毫秒数  
	  long nm = 1000*60;//一分钟的毫秒数  
	  long ns = 1000;//一秒钟的毫秒数  
	  
	protected long timeLeft;

	protected CharSequence endText = "倒计时结束";
	long nowMillionsTime;
	long lastTime;
	Handler handler = new Handler();
	protected boolean running;
	SimpleDateFormat sdfs = new SimpleDateFormat(
			"DD天HH小时mm分ss秒");
	protected String times;

	public TimerTextView(Context context) {
		super(context);
		init();
	}

	public TimerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
	}

	public void setTimeMillions(long time) {
		timeLeft = time-3600000;

		start();

	}

	/**
	 * 倒计时结束
	 */
	private void timerEnd() {
		setText(endText);
	}

	public void setEndText(CharSequence endText) {
		this.endText = endText;
	}

	@Override
	public void run() {

		if (!running) {
			return;
		}

		if (timeLeft > 0) {
			timeLeft -= 1000;
			//timeLeft/(1000*24*60*60))+"天"+
	        String str = (((timeLeft/(1000*24*60*60))*24+timeLeft%nd/nh)+"小时"+timeLeft%nd%nh/nm+"分钟"+timeLeft%nd%nh%nm/ns+"秒");
			setText("距离结束：" +str);

		} else {
			timerEnd();
			setText("");
			running = false;
		}
		if (running) {
			postDelayed(this, 1000);
		}
	}

	public void stop() {
		running = false;
	}

	public void start() {

		if (running) {
			return;
		}
		removeCallbacks(this);
		running = true;
		postDelayed(this, 1000);
	}
}
