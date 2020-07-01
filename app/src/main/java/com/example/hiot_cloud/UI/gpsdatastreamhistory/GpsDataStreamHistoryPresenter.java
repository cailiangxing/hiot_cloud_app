package com.example.hiot_cloud.UI.gpsdatastreamhistory;

import com.example.hiot_cloud.UI.base.BasePresenter;
import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.data.bean.UpDataStreamGpsBean;
import com.example.hiot_cloud.test.networktest.ResultBase;

import java.util.List;

import javax.inject.Inject;

/**
 * GPS数据可视化功能模块presenter类
 */
class GpsDataStreamHistoryPresenter extends BasePresenter<GpsDataStreamHistoryView> {
    @Inject
    DataManager dataManager;

    @Inject
    public GpsDataStreamHistoryPresenter() {
    }

    /**
     * 加载GPS数据
     *
     * @param upDataStreamId
     */
    public void loadGpsDataStreamHistory(String upDataStreamId) {
        subscrib(dataManager.getGpsUpDataStreamHistory(upDataStreamId), new RequestCallback<ResultBase<List<UpDataStreamGpsBean>>>() {
            @Override
            public void onNext(ResultBase<List<UpDataStreamGpsBean>> resultBase) {
                super.onNext(resultBase);
                if (resultBase.getData() != null) {
                    getView().showData(resultBase.getData());
                }
            }
        });
    }
}
