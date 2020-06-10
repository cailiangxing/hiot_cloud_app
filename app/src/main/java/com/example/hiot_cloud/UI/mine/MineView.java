package com.example.hiot_cloud.UI.mine;


import com.example.hiot_cloud.UI.base.BaseView;
import com.example.hiot_cloud.data.bean.UserBean;

/**
 * 用户中心View层接口
 */
public interface MineView extends BaseView {
    /**
     * 刷新用户信息
     *
     * @param userBean
     */
    void refreshUserInfo(UserBean userBean);

    void refreshUserHead(String url);

    /**
     * 重新登录的处理
     */
    void tokenOut();
}
