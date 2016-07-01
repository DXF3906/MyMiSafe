package com.qianfeng.mymisafe.welcome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 初始界面
 * Created by asus on 2016/7/1.
 */

public class InitAcitvity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(300);
                InitAcitvity.this.startActivity(new Intent(InitAcitvity.this,WelcomeActivity.class));
            }
        }.start();
    }
}
