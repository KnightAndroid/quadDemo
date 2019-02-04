package com.android.quard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.quard.view.CircleWaveProgressView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private CircleWaveProgressView circleWaveProgressView;
    private TextView tv_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView控件
        tv_value = findViewById(R.id.tv_value);
        //进度条控件
        circleWaveProgressView = findViewById(R.id.circle_progress);
        //是否绘制第二层波浪
        circleWaveProgressView.isSetCanvasSecondWave(true);
        //将TextView设置进度条里
        circleWaveProgressView.setTextViewVaule(tv_value);
        //设置字体数值显示监听
        circleWaveProgressView.setUpdateTextListener(new CircleWaveProgressView.UpdateTextListener() {
            @Override
            public String updateText(float interpolatedTime, float currentProgress, float maxProgress) {
                //取一位整数和并且保留两位小数
                DecimalFormat decimalFormat=new DecimalFormat("0.00");
                String text_value = decimalFormat.format(interpolatedTime * currentProgress / maxProgress * 100)+"%";
                //最终把格式好的内容(数值带进进度条)
                return text_value ;
            }
        });
        //设置进度和时间
        circleWaveProgressView.setProgress(99,2500);
    }
}
