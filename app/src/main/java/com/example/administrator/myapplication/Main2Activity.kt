package com.example.administrator.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat

class Main2Activity : AppCompatActivity() {
    var hour: EditText? = null;
    var min: EditText? = null;
    var second: EditText? = null;
    var a: String? = null;
    var b: String? = null;
    var c: String? = null;
    var timer: TimerTextView? = null;
    var start: Button? = null;
    internal var sdf = SimpleDateFormat("HH:mm:ss")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        hour = findViewById(R.id.edit_hour) as EditText;
        min = findViewById(R.id.edit_min) as EditText;
        second = findViewById(R.id.edit_sec) as EditText;
        timer = findViewById(R.id.timer) as TimerTextView;
        start = findViewById(R.id.go_start) as Button;


        start!!.setOnClickListener(View.OnClickListener {
            if (hour!!.getText().toString().trim().equals("")) {
                a = "00"
            } else if (hour!!.getText().toString().trim().length == 1) {
                a = "0" + hour!!.getText().toString().trim()
            } else {
                a = hour!!.getText().toString().trim()

            }
            if (min!!.text.toString().trim().equals("")) {
                b = "00"
            } else if (min!!.text.toString().trim().length == 1) {
                b = "0" + min!!.text.toString().trim()

            } else {

                b = min!!.text.toString().trim()
            }
            if (second!!.text.toString().trim().equals("")) {
                c = "00"
            } else if (second!!.text.toString().trim().length == 1) {
                c = "0" + second!!.text.toString().trim()
            } else {
                c = second!!.text.toString().trim()

            }
            if((a + ":" + b + ":" + c).equals("00:00:00")){
                Toast.makeText(this@Main2Activity,"请输入正确的时间",Toast.LENGTH_LONG).show();
                return@OnClickListener
            }
            timer!!.setTimeMillions(-(sdf.parse(a + ":" + b + ":" + c).time));
            Toast.makeText(this@Main2Activity, "点击了"+ -(sdf.parse(a + ":" + b + ":" + c).time), Toast.LENGTH_LONG).show()
        })

    }
}
