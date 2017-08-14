package com.example.yuliuyu.qifeiba.net;

import com.example.yuliuyu.qifeiba.Bean.WeatherBean;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/7/24 10:32
 * 修改人：yuliuyu
 * 修改时间：2017/7/24 10:32
 * 修改备注：
 */
public interface QfbServer {
    @GET("query")
    Observable<List<WeatherBean>> weather(@Query("key") String key,@Query("city") String city,@Query("province") String province);
    @GET("ip")
    Observable<Object> weather(@Query("key") String key,@Query("ip") String ip);


}
