package com.example.hiot_cloud.UI.login;

import android.text.TextUtils;

import com.example.hiot_cloud.UI.base.BasePresenter;
import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.test.networktest.LoginResultDTO;
import com.example.hiot_cloud.test.networktest.ResultBase;
import com.example.hiot_cloud.utils.Constants;

import javax.inject.Inject;

/**
 * 登录presenter类
 */
class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    DataManager dataManager;

    @Inject
    public LoginPresenter() {
    }

    /**
     * 登录
     *
     * @param email
     * @param password
     */
    public void login(String email, String password) {
        subscrib(dataManager.login(email, password), new RequestCallback<ResultBase<LoginResultDTO>>() {
            @Override
            public void onNext(ResultBase<LoginResultDTO> resultBase) {
                if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //如果登录身份正确，弹出登录成功，跳转主界面
                    if (resultBase != null && resultBase.getData() != null) {
                        //弹出登录成功
                        getView().showMessage("登录成功！");

                        //跳转主界面
                        getView().loginSucc();
                    }
                } else {
                    //如果登录身份错误，弹出服务端返回的错误信息
                    if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                        getView().showMessage(resultBase.getMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().showMessage("当前网络无法访问，请稍后再试！");
            }
        });
    }
}
