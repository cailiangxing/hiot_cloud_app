package com.example.hiot_cloud.UI.switchdatastreamhistory;

import com.example.hiot_cloud.UI.base.BaseView;
import com.example.hiot_cloud.data.bean.UpDataStreamSwitchBean;

import java.util.List;

/**
 * 通道历史数据View层接口
 */
interface LineChartView extends BaseView {
    /**
     * 把返回的通道历史数据显示到图表中
     *
     * @param dataList
     */
    void showDataHistory(List<UpDataStreamSwitchBean> dataList);
}
