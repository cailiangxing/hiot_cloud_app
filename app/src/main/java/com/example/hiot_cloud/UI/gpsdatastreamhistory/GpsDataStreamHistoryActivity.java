package com.example.hiot_cloud.UI.gpsdatastreamhistory;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.hiot_cloud.R;
import com.example.hiot_cloud.UI.base.BaseActivity;
import com.example.hiot_cloud.data.bean.UpDataStreamGpsBean;
import com.example.hiot_cloud.utils.Constants;
import com.example.hiot_cloud.utils.ShaUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GpsDataStreamHistoryActivity extends BaseActivity<GpsDataStreamHistoryView, GpsDataStreamHistoryPresenter> implements GpsDataStreamHistoryView, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, GeocodeSearch.OnGeocodeSearchListener {

    private static final String TAG = "GpsActivity";

    @Inject
    GpsDataStreamHistoryPresenter presenter;

    @BindView(R.id.tv_gps_data_history)
    TextView tvGpsDataHistory;

    @BindView(R.id.toolbar_gps_data_history)
    Toolbar toolbarGpsDataHistory;

    @BindView(R.id.map_view)
    MapView mMapView;

    private String upDataStreamId;

    private AMap aMap;

    GeocodeSearch geocodeSearch = new GeocodeSearch(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_data_stream_history);
        ButterKnife.bind(this);
        upDataStreamId = getIntent().getStringExtra(Constants.INTENT_EXTRA_UP_DATASTREAM_ID);
        //初始化控件
        initView();
        //初始化地图
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        initMap();
        Log.d(TAG, "onCreate: " + ShaUtils.sHA1(this));
    }


    @Override
    public GpsDataStreamHistoryPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
        presenter.loadGpsDataStreamHistory(upDataStreamId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setSupportActionBar(toolbarGpsDataHistory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGpsDataHistory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    /**
     * 初始化地图
     */
    private void initMap() {
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        //初始化地图
        UiSettings settings = aMap.getUiSettings();
        //设置比例尺默认显示
        settings.setScaleControlsEnabled(true);
        //显示定位按钮
        settings.setMyLocationButtonEnabled(true);
        //指南针
        settings.setCompassEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(11));

        //设置监听事件
        aMap.setOnMarkerClickListener(this);
        aMap.setOnInfoWindowClickListener(this);

        //创建逆地理编码和设置事件监听
        geocodeSearch.setOnGeocodeSearchListener(this);
    }


    @Override
    public void showData(List<UpDataStreamGpsBean> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        List<LatLng> latLngList = new ArrayList<>();
        for (UpDataStreamGpsBean upDataStreamGpsBean : dataList) {
            LatLng latLng = new LatLng(Double.valueOf(upDataStreamGpsBean.getLatitude()),
                    Double.valueOf(upDataStreamGpsBean.getLongitude()));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng)
                    .title(upDataStreamGpsBean.getTiming())
                    .draggable(true)
                    .setFlat(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 15, 0, 0)), 100, null);
            aMap.addMarker(markerOptions);
            latLngList.add(latLng);
        }
        //画线
        aMap.addPolyline(new PolylineOptions().addAll(latLngList).geodesic(true).color(Color.RED));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        RegeocodeQuery regeocodeQuery = new RegeocodeQuery(
                new LatLonPoint(marker.getPosition().latitude, marker.getPosition().longitude), 100, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(regeocodeQuery);
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int resultId) {
        if (resultId == 1000) {
            if (regeocodeResult != null && regeocodeResult.getRegeocodeAddress() != null && regeocodeResult.getRegeocodeAddress().getFormatAddress() != null) {
                showMessage(regeocodeResult.getRegeocodeAddress().getFormatAddress());
            }
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
