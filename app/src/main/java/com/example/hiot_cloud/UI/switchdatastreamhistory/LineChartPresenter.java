package com.example.hiot_cloud.UI.switchdatastreamhistory;

import com.example.hiot_cloud.UI.base.BasePresenter;
import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.data.bean.UpDataStreamSwitchBean;
import com.example.hiot_cloud.test.networktest.ResultBase;

import java.util.List;

import javax.inject.Inject;

/**
 * 通道历史数据Presenter类
 */
class LineChartPresenter extends BasePresenter<LineChartView> {
    @Inject
    DataManager dataManager;

    @Inject
    public LineChartPresenter() {
    }

    /**
     * 加载通道历史数据
     *
     * @param upDataStreamId
     */
    public void loadUpDataStreamHistory(String upDataStreamId) {
        subscrib(dataManager.getUpDataStreamHistory(upDataStreamId), new RequestCallback<ResultBase<List<UpDataStreamSwitchBean>>>() {
            @Override
            public void onNext(ResultBase<List<UpDataStreamSwitchBean>> resultBase) {
                super.onNext(resultBase);
                if (resultBase.getData() != null) {
                    getView().showDataHistory(resultBase.getData());
                }
            }
        });
    }
}
