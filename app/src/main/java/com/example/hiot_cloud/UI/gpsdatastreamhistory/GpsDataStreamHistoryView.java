package com.example.hiot_cloud.UI.gpsdatastreamhistory;

import com.example.hiot_cloud.UI.base.BaseView;
import com.example.hiot_cloud.data.bean.UpDataStreamGpsBean;

import java.util.List;

/**
 * GPS数据可视化功能模块view层接口
 */
interface GpsDataStreamHistoryView extends BaseView {

    /**
     * 显示数据
     *
     * @param dataList
     */
    void showData(List<UpDataStreamGpsBean> dataList);
}
