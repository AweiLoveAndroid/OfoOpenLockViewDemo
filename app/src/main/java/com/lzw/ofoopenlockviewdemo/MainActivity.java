package com.lzw.ofoopenlockviewdemo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private RadarDiffuseView radarDiffuseView;
    private RelativeLayout relativeLayout;
    private TextView textView;
    private ImageView imageView;

    private int progress = 0;
    private Handler mHandler = new Handler();
    private TextView contents;
    public static Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        typeface = Typeface.createFromAsset(getAssets(),Constant.FONT_SPACE);

        textView = (TextView) findViewById(R.id.textview_progress);
        contents = (TextView) findViewById(R.id.contents);
        imageView = (ImageView) findViewById(R.id.image_progress);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        radarDiffuseView = (RadarDiffuseView) findViewById(R.id.radar_view);

        textView.setTypeface(typeface);
        contents.setTypeface(typeface);



       /* radarDiffuseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


        //开始调用
        mHandler.postDelayed(new getProgressTask(),0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        radarDiffuseView.openDiffseBoolean(false);
        radarDiffuseView.setRadarBool(false);

        //停止调用
        mHandler.removeCallbacksAndMessages(null);
    }


    private class getProgressTask implements Runnable{
        public getProgressTask() {
            super();
        }

        @Override
        public void run() {
            if(progress<100){
                mHandler.postDelayed(this, 250);
                progress++;
                textView.setText(progress+" %");
            }else{
                contents.setText("开锁完成");
                textView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
            }

        }
    }
}
