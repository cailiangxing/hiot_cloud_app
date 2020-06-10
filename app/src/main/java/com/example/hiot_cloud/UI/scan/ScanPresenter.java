package com.example.hiot_cloud.UI.scan;


import com.example.hiot_cloud.UI.base.BasePresenter;
import com.example.hiot_cloud.data.DataManager;
import com.example.hiot_cloud.test.networktest.ResultBase;

import javax.inject.Inject;

/**
 * 扫一扫模块Presenter类
 */
class ScanPresenter extends BasePresenter<ScanView> {


    @Inject
    DataManager dataManager;

    @Inject
    public ScanPresenter() {

    }

    /**
     * 绑定设备
     *
     * @param deviceId
     */
    public void bindDevice(String deviceId) {
        subscrib(dataManager.bindDevice(deviceId), new RequestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                super.onNext(resultBase);
                getView().showMessage("绑定成功");
                getView().bindDeviceSucc();
            }
        });

    }
}
