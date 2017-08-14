package com.example.yuliuyu.qifeiba.app;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;
import com.baidu.mapapi.SDKInitializer;
import com.example.yuliuyu.qifeiba.net.log.OkHttpRequestParse;
import com.example.yuliuyu.qifeiba.net.log.OkHttpResponseParse;
import com.remair.log.LogUtils;

/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/8/2 16:10
 * 修改人：yuliuyu
 * 修改时间：2017/8/2 16:10
 * 修改备注：
 */
public class QfbApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init(true,"qifeiba");
        LogUtils.addParserClass(OkHttpResponseParse.class, OkHttpRequestParse.class);
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        SDKInitializer.initialize(getApplicationContext());
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }
}
