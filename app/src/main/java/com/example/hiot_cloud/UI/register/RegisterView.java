package com.example.hiot_cloud.UI.register;

import com.example.hiot_cloud.UI.base.BaseView;

/**
 * 注册模块VIEW层接口
 */
interface RegisterView extends BaseView {

    /**
     * 注册成功后的处理
     *
     * @param userName
     * @param password
     */
    void registerSucc(String userName, String password);


    /**
     * 自动登录后的处理
     */
    void loginSucc();
}
