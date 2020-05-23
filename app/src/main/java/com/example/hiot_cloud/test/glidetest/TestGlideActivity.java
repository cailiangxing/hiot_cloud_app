package com.example.hiot_cloud.test.glidetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.hiot_cloud.R;
import com.example.hiot_cloud.utils.ImageUtils;

public class TestGlideActivity extends AppCompatActivity {

    final String url = "http://pic1.win4000.com/wallpaper/2018-08-16/5b750e40cbed0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_glide);

        final ImageView ivGlide = findViewById(R.id.iv_glide_test);
        Button btnGlideLoad = findViewById(R.id.btn_glide_load_test);
        btnGlideLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(TestGlideActivity.this)
                        .load("http://p1.pstatp.com/large/166200019850062839d3")
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        .transition(new DrawableTransitionOptions().crossFade())
                        .into(ivGlide);
            }
        });

        /**
         * 实用工具类显示图片
         */
        Button btnUtilsLoad = findViewById(R.id.btn_glide_utils_test);
        btnUtilsLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUtils.show(TestGlideActivity.this, ivGlide, url);
            }
        });
    }
}
