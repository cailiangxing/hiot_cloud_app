package com.example.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiot_cloud.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitActivity extends AppCompatActivity {

    private static final String TAG = "TestRetrofitActivity";
    private Retrofit retrofit;
    private TestRetrofitService service;
    private Gson gson = new Gson();
    private EditText etToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);

        //取到edit_token
        etToken = findViewById(R.id.et_token_retrofit);
        //创建retrofit和service
        createRetrofit();

        //百度
        Button btnTest = findViewById(R.id.btn_retrofit_baidu);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
        //登录
        Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("clxtest2","adminadmin","app");
            }
        });
        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_retrofit_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo("e82a427684dd4632988c5e9bd864ee04_740082e714e14814bf8c2404fed88866_use");
            }
        });
        //修改邮箱
        final Button btnUpdateEmail = findViewById(R.id.btn_retrofit_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail("e82a427684dd4632988c5e9bd864ee04_740082e714e14814bf8c2404fed88866_use","clx@qq.com");
            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_retrofit_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    /**
     * 注册
     */
    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("clxtest2");
        userBean.setEmail("clxclx@qq.com");
        userBean.setPassword("adminadmin");
        userBean.setUserType("1");
        Call<ResponseBody> call = service.register(userBean);
        callEnqueue(call);
    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization, String email) {
        Call<ResponseBody> call = service.updateEmail(authorization, email);
        callEnqueue(call);
    }

    /**
     * 获取用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
//        Call<ResponseBody> call = service.getUserInfo(authorization);
//        callEnqueueUserInfo(call);
//        callEnqueue(call);
        /**
         * 自动解析
         */
        Call<ResultBase<UserBean>> call = service.getUserInfo2(authorization);
        call.enqueue(new Callback<ResultBase<UserBean>>() {
            @Override
            public void onResponse(Call<ResultBase<UserBean>> call, Response<ResultBase<UserBean>> response) {
                ResultBase<UserBean> resultBase = response.body();
                if (resultBase != null && resultBase.getData() != null){
                    resultBase.getData();
                    String str = resultBase.getData().getUsername()+","+resultBase.getData().getEmail();
                    Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultBase<UserBean>> call, Throwable t) {

            }
        });

    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login(userName, password, loginCode);
        callEnqueueLogin(call);
//        callEnqueue(call);
    }

    private void callEnqueue(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void callEnqueueLogin(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
//                    Log.d(TAG, "onResponse: " + response.body().string());
                    Type type = new TypeToken<ResultBase<LoginResultDTO>>(){}.getType();
                    ResultBase<LoginResultDTO> loginResult = gson.fromJson(response.body().string(),type);
                    if (loginResult != null && loginResult.getData() != null){
                        String token = loginResult.getData().getTokenValue();
                        etToken.setText(token);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void callEnqueueUserInfo(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
//                    Log.d(TAG, "onResponse: " + response.body().string());
                    Type type = new TypeToken<ResultBase<UserBean>>(){}.getType();
                    ResultBase<UserBean> resultBase = gson.fromJson(response.body().string(),type);
                    if (resultBase != null && resultBase.getData() != null) {
                        UserBean userBean = resultBase.getData();
                        String str = String.format("用户名：%s,密码：%s,email：%s,用户类型：%s",
                                userBean.getUsername(), userBean.getPassword(), userBean.getEmail(), userBean.getUserType());
                        Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                    if (resultBase != null && resultBase.getMsg() != null){
                        Toast.makeText(TestRetrofitActivity.this,resultBase.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void test() {
        Call<ResponseBody> call = service.test();
        callEnqueue(call);
    }

    private void createRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.basUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(TestRetrofitService.class);
    }
}
