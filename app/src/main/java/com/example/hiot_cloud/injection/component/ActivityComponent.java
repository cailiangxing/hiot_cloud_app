/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hiot_cloud.injection.component;


import com.example.hiot_cloud.UI.devicedetail.DeviceDetailActivity;
import com.example.hiot_cloud.UI.devicelist.DeviceListFragment;
import com.example.hiot_cloud.UI.gpsdatastreamhistory.GpsDataStreamHistoryActivity;
import com.example.hiot_cloud.UI.login.LoginActivity;
import com.example.hiot_cloud.UI.main.MainActivity;
import com.example.hiot_cloud.UI.main.SplashActivity;
import com.example.hiot_cloud.UI.mine.MineFragment;
import com.example.hiot_cloud.UI.register.RegisterActivity;
import com.example.hiot_cloud.UI.scan.ScanActivity;
import com.example.hiot_cloud.UI.switchdatastreamhistory.LineChartActivity;
import com.example.hiot_cloud.injection.PerActivity;
import com.example.hiot_cloud.injection.module.ActivityModule;
import com.example.hiot_cloud.test.MVPtest.TestMvpActivity;
import com.example.hiot_cloud.test.networktest.TestNetworkPackActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(TestMvpActivity testMvpActivity);

    void inject(TestNetworkPackActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(RegisterActivity activity);

    void inject(MineFragment fragment);

    void inject(ScanActivity activity);

    void inject(DeviceListFragment fragment);

    void inject(DeviceDetailActivity activity);

    void inject(LineChartActivity activity);

    void inject(GpsDataStreamHistoryActivity activity);

    @Component.Builder
    interface ActivityComponentBuilder {

        ActivityComponent build();

        ActivityComponentBuilder applicationComponent(ApplicationComponent applicationComponent);

        ActivityComponentBuilder activityModule(ActivityModule activityModule);
    }
}
