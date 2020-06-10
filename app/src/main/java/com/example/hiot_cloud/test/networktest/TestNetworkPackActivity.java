package com.example.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiot_cloud.R;
import com.example.hiot_cloud.UI.base.BaseActivity;
import com.example.hiot_cloud.UI.base.BasePresenter;
import com.example.hiot_cloud.data.DataManager;

import javax.inject.Inject;

/**
 * 网络封装层测试类
 */
public class TestNetworkPackActivity extends BaseActivity implements TestNetworkPackView {

    private static final String TAG = "TestNetworkPackActivity";
    private EditText etToken;

    @Inject
    DataManager dataManager;

    @Inject
    TestNetworkPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_network_pack);

        //editText
        etToken = findViewById(R.id.et_network_pack_token);

        //登录
        Button btnLogin = findViewById(R.id.btn_network_pack_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login("wlwysp2", "abc123");
                login("wlwysp2", "abc123");

            }
        });


        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_network_pack_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getUserInfo(etToken.getText().toString());


            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_network_pack_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateEmail(etToken.getText().toString(), "ysp12@qq.com");


            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_network_pack_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.register("wlwysp5", "abc123", "wlwysp5@qq.com");


            }
        });


    }

    @Override
    public BasePresenter createPresenter() {

        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);

    }

    /**
     * 登录
     * @param userName
     * @param password
     */

    private void login(String userName, String password) {
/*        dataManager.login(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<LoginResultDTO> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            etToken.setText(resultBase.getData().getTokenValue());
                        }else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())){
                            Toast.makeText(TestNetworkPackActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onNext: "+resultBase.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

    }


    @Override
    public void showToken(String token) {
        etToken.setText(token);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
