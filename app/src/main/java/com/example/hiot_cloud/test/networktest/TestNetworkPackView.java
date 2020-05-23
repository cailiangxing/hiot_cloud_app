package com.example.hiot_cloud.test.networktest;

import com.example.hiot_cloud.UI.base.BaseView;

/**
 * 网络封装层MVP架构VIEW接口
 */
public interface TestNetworkPackView extends BaseView {

    /**
     * 显示Token
     * @param token
     */
    void showToken(String token);

    /**
     * 显示信息
     * @param message
     */
    void showMessage(String message);
}
