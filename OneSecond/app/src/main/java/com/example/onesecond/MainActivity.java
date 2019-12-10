package com.example.onesecond;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvTime;
    TextView tvNormal;
    TextView tvGod;
    TextView tvAction;
    TextView tvDes;
    LinearLayout llBtn;
    TextView tvIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initListener();
    }
    private void findView(){
        tvTime = (TextView)findViewById(R.id.tv_time);
        tvAction = (TextView)findViewById(R.id.tv_action);
        tvNormal = (TextView)findViewById(R.id.tv_normal);
        tvGod = (TextView)findViewById(R.id.tv_god);
        tvDes = (TextView)findViewById(R.id.tv_des);
        llBtn = (LinearLayout)findViewById(R.id.ll_btn);
        tvIndex = (TextView)findViewById(R.id.tv_index);
    }
    long downTime;
    long upTime;
    int godTime;
    int normalTime;
    int index;
    private  void initListener(){
        llBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //因为时间有多种，用于区分什么事件
                if(event.getAction() == MotionEvent.ACTION_DOWN){index++;
                    tvIndex.setText("运行了"+index+"次");
                    downTime = System.currentTimeMillis();
                    setVisibleByDownToBtn();
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    upTime = System.currentTimeMillis();
                    double  time = calculateTime();
                    tvTime.setText(""+time);
                    //需要转换为字符串
                    if(Math.abs(time - 1 )> 0.1){
                        normalTime++;
                        tvNormal.setText("凡人"+normalTime+"次");
                        setVisibleToBtn();
                    }else{
                        godTime++;
                        tvGod.setText("大神"+godTime+"次");
                        setVisibleToBtn();
                    }

                }else if(event.getAction() == MotionEvent.ACTION_MOVE){

                }
                return false;
            }
        });
    }

    private void setVisibleToBtn() {
        tvAction.setVisibility(View.GONE);
        tvDes.setVisibility(View.VISIBLE);
        tvTime.setVisibility(View.VISIBLE);
    }
    private void setVisibleByDownToBtn() {
        tvAction.setVisibility(View.VISIBLE);
        tvDes.setVisibility(View.GONE);
        tvTime.setVisibility(View.GONE);
    }
    private double calculateTime() {
        return (double)(upTime - downTime)/1000;
    }
}
