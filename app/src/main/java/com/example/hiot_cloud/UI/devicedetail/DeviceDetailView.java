package com.example.hiot_cloud.UI.devicedetail;

import com.example.hiot_cloud.UI.base.BaseView;
import com.example.hiot_cloud.data.bean.DeviceDetailBean;

/**
 * 设备详情模块view接口层
 */
interface DeviceDetailView extends BaseView {

    /**
     * 根据设备详情内容显示到界面
     *
     * @param data
     */
    void setDeviceDetail(DeviceDetailBean data);
}
