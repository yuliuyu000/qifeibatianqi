package com.example.yuliuyu.qifeiba.net;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/8/7 10:30
 * 修改人：yuliuyu
 * 修改时间：2017/8/7 10:30
 * 修改备注：
 */
public class TransformerUtil {
    public static <T> Observable.Transformer<T, T> defaultSchedulers(){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                        .mainThread());
            }
        };
    }

    /**
     * 设置网络请求和数据返回都在IO线程
     */
    public static <T> Observable.Transformer<T, T> allIo() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io());
            }
        };
    }



}
