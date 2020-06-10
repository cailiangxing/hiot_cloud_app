package com.example.hiot_cloud.UI.devicelist;


import com.example.hiot_cloud.UI.base.BaseView;
import com.example.hiot_cloud.data.bean.DeviceBean;

import java.util.List;

/**
 * 设备列表功能View层接口
 */
interface DeviceListView extends BaseView {

    /**
     * 显示设备列表
     *
     * @param deviceList
     */
    void showDeviceList(List<DeviceBean> deviceList);
}
