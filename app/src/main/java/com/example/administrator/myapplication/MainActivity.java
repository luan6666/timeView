package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TimeView timeView;
    TextView time_text,goNext;
    SimpleDateFormat hour = new SimpleDateFormat("HH");
    SimpleDateFormat min = new SimpleDateFormat("mm");
    Date date = new Date();
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat second = new SimpleDateFormat("ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeView = findViewById(R.id.time);

        String a = hour.format(date.getTime());
        String b = min.format(date.getTime());
        String c = second.format(date.getTime());
        timeView.setTime(Integer.valueOf(c), Integer.valueOf(b), Integer.valueOf(a));
        timeView.start();
        time_text = findViewById(R.id.time_text);
        goNext = findViewById(R.id.toNext);
        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        mHandler = new MyHandler(this);
        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_CURRENT_TIME, 500);
    }

    private Handler mHandler;
    private static final int MSG_UPDATE_CURRENT_TIME = 1;


    private static class MyHandler extends Handler {
        private WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            switch (msg.what) {
                case MSG_UPDATE_CURRENT_TIME:
                    activity.updateCurrentTime();
                    sendEmptyMessageDelayed(MSG_UPDATE_CURRENT_TIME, 500);
                    break;
                default:
                    break;
            }
        }
    }

    private void updateCurrentTime() {
        Date dates = new Date();
        String aa = time.format(dates.getTime());
        time_text.setText(aa);
    }

}
