package com.example.yuliuyu.qifeiba.location;

import android.content.Context;
import android.util.Log;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/8/2 16:16
 * 修改人：yuliuyu
 * 修改时间：2017/8/2 16:16
 * 修改备注：
 */
public class LocationHelp {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    Context mContext;


    public LocationHelp(Context context) {
        mContext = context;
        onCreate();
    }


    public void onCreate() {
        mLocationClient = new LocationClient(mContext);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

    }


    public void start() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(3000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }


    public void onDestroy() {
        // 退出时销毁定位
        mLocationClient.stop();
    }
}
