package com.example.yuliuyu.qifeiba.net;

import android.text.TextUtils;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 项目名称：Android
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：16/8/24 17:39
 * 修改人：LiuJun
 * 修改时间：16/8/24 17:39
 * 修改备注：
 */
class CommonInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
            //request = getRequest(request);
        return chain.proceed(request);
    }


    //private Request getRequest(Request request) {
    //    Request.Builder builder = request.newBuilder();
    //
    //        builder.post(body);
    //
    //    return builder.build();
    //}


    private boolean canInjectIntoBody(Request request) {
        if (request == null) {
            return false;
        }
        if (!TextUtils.equals(request.method(), "POST")) {
            return false;
        }
        RequestBody body = request.body();
        return body != null;
        //MediaType mediaType = body.contentType();
        //return mediaType != null &&
        //        TextUtils.equals(mediaType.subtype(), "x-www-form-urlencoded");
    }


    // func to inject params into url
    private void injectParamsIntoUrl(Request request, Request.Builder requestBuilder, String timestamp, String sign) {
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        httpUrlBuilder.addQueryParameter("timestamp", timestamp);
        httpUrlBuilder.addQueryParameter("sign", sign);
        requestBuilder.url(httpUrlBuilder.build());
    }


    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null) {
                request.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }


    public String getVersionName() {
        return "3.5.0";
    }
}
