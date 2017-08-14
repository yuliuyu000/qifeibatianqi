package com.example.yuliuyu.qifeiba.net;

import com.remair.log.LogUtils;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * 项目名称：qifeiba
 * 类描述：
 * 创建人：yuliuyu
 * 创建时间：2017/7/24 19:57
 * 修改人：yuliuyu
 * 修改时间：2017/7/24 19:57
 * 修改备注：
 */
public class HttpLoggerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest= chain.request();
        printRequestLog(originalRequest);
        Response response  =chain.proceed(originalRequest);
        Response.Builder builder = response.newBuilder();
        Response build = builder.build();
        ResponseBody body= build.body();
        MediaType mediaType = body.contentType();
        String resp = body.string();
        Response print = response.newBuilder()
                                 .body(ResponseBody.create(mediaType, resp))
                                 .build();
        printResult(print);
        return response.newBuilder().body(ResponseBody.create(mediaType, resp))
                       .build();
    }


    private void printRequestLog(Request originalRequest) {
        LogUtils.tag("Request").i(originalRequest);
    }
    /**
     * 打印返回日志
     *
     * @throws IOException
     */
    private void printResult(Response response) throws IOException {
        LogUtils.tag("Response").i(response);
    }

}
