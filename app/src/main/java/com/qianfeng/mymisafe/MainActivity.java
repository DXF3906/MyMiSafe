package com.qianfeng.mymisafe;

import android.content.Intent;
import android.os.Environment;
import android.os.StatFs;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.mymisafe.welcome.activity.AntiVirusActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ImageView ivImage2;//动画图片
    private Button btn_text_id;//优化分数
    private TextView tv_space_id;//手机剩余空间
    private TextView tv_picture_id;//照片数量
    private TextView tv_apk_id;//安装包数量
    private int cunt = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        intitWidget();

        //关于优化建议的操作
        aboutSuggection();



    }
    /**
     * 初始化控件
     */
    private void intitWidget() {
        ivImage2 = (ImageView) findViewById(R.id.ivImage2);//动画图片
        btn_text_id = (Button) findViewById(R.id.btn_text_id);//优化分数

        tv_space_id=(TextView)findViewById(R.id.tv_space_id);//手机剩余空间
        tv_picture_id=(TextView)findViewById(R.id.tv_picture_id);//照片数量
        tv_apk_id=(TextView)findViewById(R.id.tv_apk_id);//安装包数量
    }

    /**
     * 关于杀毒的操作
     * @param view
     */
    public void KillAction(View view){
        Intent intent=new Intent(MainActivity.this,AntiVirusActivity.class);
        startActivity(intent);
    }
    /**
     * 关于优化建议的操作
     */
    private void aboutSuggection() {
        File path = Environment.getExternalStorageDirectory(); //取得sdcard文件路径

        //剩余空间计算
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        tv_space_id.setText(availableBlocks * blockSize/1024/1024+"MB");
    }




    /**
     * 优化点击事件
     * @param view
     */
    public void YouHuaAction(View view) {
        RotateAnimation ra = new RotateAnimation(0, 360, 360f, 253f);
        ra.setDuration(2000);
        ra.setFillAfter(false);
        ra.setRepeatCount(3);// 重复播放的次数（排除默认的额一次）
        ra.setRepeatMode(RotateAnimation.RESTART);

        // 将透明动画效果作用于指定的控件上
        ivImage2.startAnimation(ra);
        ra.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(MainActivity.this, "系统已优化成最佳状态", Toast.LENGTH_SHORT).show();
                cunt = 100;
                btn_text_id.setText(cunt + "");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (cunt < 100) {
                    cunt += 10;
                    btn_text_id.setText(cunt + "");

                }
            }
        });
    }

}
