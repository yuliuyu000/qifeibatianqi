package com.example.yuliuyu.qifeiba.net;

import com.example.yuliuyu.qifeiba.Bean.WeatherBean;
import com.example.yuliuyu.qifeiba.net.converter.FastJsonConverterFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/7/24 10:32
 * 修改人：yuliuyu
 * 修改时间：2017/7/24 10:32
 * 修改备注：
 */
public class QfbHttpUtil {
    public QfbServer mQfbServer;

    private volatile OkHttpClient mOkHttpClient;

    private static class SingletonHolder {
        static QfbHttpUtil INSTANCE = new QfbHttpUtil();
    }


    public static QfbHttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (this) {
                if (mOkHttpClient == null) {
                    long timeout = 10L;
                    mOkHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(timeout, TimeUnit.SECONDS)
                            .readTimeout(timeout, TimeUnit.SECONDS)
                            .writeTimeout(timeout, TimeUnit.SECONDS).retryOnConnectionFailure(true)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }


    public QfbHttpUtil() {
        final OkHttpClient.Builder QfbOkhttpBuilder = getOkHttpClient().newBuilder();
        RxJavaCallAdapterFactory xJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
        QfbOkhttpBuilder.addInterceptor(new HttpLoggerInterceptor());
        QfbOkhttpBuilder.addInterceptor(new CommonInterceptor());
        OkHttpClient okHttpClient = QfbOkhttpBuilder.build();
        FastJsonConverterFactory fastJsonConverterFactory = FastJsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                                                  .addConverterFactory(fastJsonConverterFactory)
                                                  .addCallAdapterFactory(xJavaCallAdapterFactory)
                                                  .baseUrl("http://apicloud.mob.com/v1/weather/")
                                                  .build();
        mQfbServer = retrofit.create(QfbServer.class);
    }


    public Observable<List<WeatherBean>> weather(String key, String city, String province) {
        return mQfbServer.weather(key, city, province).compose(TransformerUtil.<List<WeatherBean>>defaultSchedulers());
    }


    public Observable<Object> weather(String key, String ip) {
        return mQfbServer.weather(key, ip).compose(new Observable.Transformer<Object, Object>() {
            @Override
            public Observable<Object> call(Observable<Object> objectObservable) {
                return objectObservable.subscribeOn(Schedulers.io());
            }
        });
    }
}
