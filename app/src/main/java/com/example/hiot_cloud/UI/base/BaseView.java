package com.example.hiot_cloud.UI.base;

/**
 * MVP架构视图层接口
 */
public interface BaseView {
    /**
     * 吐司消息
     * @param message
     */
    void showMessage(String message);

    /**
     * token失效的处理
     */
    void tokenOut();
}
