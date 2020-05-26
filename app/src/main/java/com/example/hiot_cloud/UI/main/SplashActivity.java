package com.example.hiot_cloud.UI.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.UI.base.BaseActivity;
import com.example.hiot_cloud.UI.base.BasePresenter;
import com.example.hiot_cloud.UI.login.LoginActivity;
import com.example.hiot_cloud.data.SharedPreferencesHelper;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    private static final int HANDLER_MSG_OPEN_NEW = 1;

    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    private Handler handler = new Handler(){

        private Intent intent;

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == HANDLER_MSG_OPEN_NEW){

                //如果已登录，跳转主界面
                if (!TextUtils.isEmpty(sharedPreferencesHelper.getUserToken())) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    //如果未登录，跳转登录界面
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //设置定时器

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(HANDLER_MSG_OPEN_NEW);
            }
        },3000);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void InjectIndependies() {
        getActivityComponent().inject(this);
    }
}
