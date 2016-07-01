package com.qianfeng.mymisafe.welcome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qianfeng.mymisafe.MainActivity;
import com.qianfeng.mymisafe.R;
import com.qianfeng.mymisafe.welcome.adapter.MyAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * 欢迎界面
 * Created by asus on 2016/7/1.
 */

public class WelcomeActivity extends AppCompatActivity {
    @ViewInject(R.id.vp_id)
    private ViewPager mVp;
    @ViewInject(R.id.ll_container_id)
    private ViewPager ll;
    private List<View> ds;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //思路：
        //①界面控件实例的获取
        ViewUtils.inject(this);
        //②关于ViewPager的操作-->ViewPager决定小圆点的联动
        aboutViewPager();
        //③关于线性布局的操作--》小圆点决定ViewPager页面的联动
        aboutLittleDots();
    }

    /**
     * 关于小圆点的操作
     */
    private void aboutLittleDots() {
        //思路：
        //小圆点的个数由ViewPager中界面的个数决定
        //通过循环，在父控件中添加小圆点所对应控件ImageView的实例
        View.OnClickListener listener=new MyDotClickListenre();
        for(int i=0;i<ds.size()-1;i++){
            //①构建ImageViewd 实例
            ImageView iv=new ImageView(this);
            //②设置ImageView的属性
            iv.setImageResource(R.drawable.dot_selector);
            iv.setEnabled(true);

            //添加加监听器，决定ViewPager目前选中的页面
            iv.setTag(i);
            iv.setOnClickListener(listener);

            //③将控件设置到父控件
            ll.addView(iv);
        }
        ll.getChildAt(0).setEnabled(false);

    }
    private final class MyDotClickListenre implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            mVp.setCurrentItem((Integer)view.getTag());
        }
    }

    /**
     * 关于ViewPager的操作
     */
    private void aboutViewPager() {
        //数据源
        ds=new LinkedList<>();
        fillDataSource();
        //适配器
        PagerAdapter adapter=new MyAdapter(ds,mVp);
        //设置适配器
        mVp.setAdapter(adapter);
        //添加监听器
        mVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                //ViewPager决定小圆点的状态
                //思路：
                //①让所有小圆点恢复可点击状态
                for (int i=0;i<ll.getChildCount();i++){
                    View view=ll.getChildAt(i);
                    view.setEnabled(true);
                }
                //②让当前位置的小圆点不可点击
                ll.getChildAt(position).setEnabled(false);
            }
        });
    }



    /**
     * 填充数据数据源
     */
    private void fillDataSource() {
        //根据图片的张数，构建ImageVeiw的实例
        int[]iamgeIds={R.mipmap.welcome1,R.mipmap.welcome2,R.mipmap.welcome3};
        for (int imageId:iamgeIds){
            ImageView iv=new ImageView(this);
            iv.setImageResource(imageId);
            ds.add(iv);

        }
        View view=View.inflate(this,R.layout.last_welcome_page,null);
        CheckBox openAutoModel=(CheckBox)view.findViewById(R.id.cb_open_auto_mode_id);
        CheckBox joinPlan= (CheckBox)view.findViewById(R.id.cb_join_plan_id);

        openAutoModel.setOnCheckedChangeListener(new MyListener());
        joinPlan.setOnCheckedChangeListener(new MyListener());

        ImageView enterIv=(ImageView) view.findViewById(R.id.iv_id);
        enterIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });

        ds.add(view);
    }
    private  final  class MyListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()){
                case R.id.cb_open_auto_mode_id:
                    if (b){
                        Toast.makeText(WelcomeActivity.this,compoundButton.getText()+"选中了哦",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(WelcomeActivity.this,compoundButton.getText()+"取消了哇。。。。",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.cb_join_plan_id:
                    if (b){
                        Toast.makeText(WelcomeActivity.this,compoundButton.getText()+"选中了哦",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(WelcomeActivity.this,compoundButton.getText()+"取消了哇。。。。",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }


}
