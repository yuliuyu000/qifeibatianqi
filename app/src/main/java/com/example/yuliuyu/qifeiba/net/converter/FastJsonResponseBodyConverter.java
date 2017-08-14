package com.example.yuliuyu.qifeiba.net.converter;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.example.yuliuyu.qifeiba.net.ApiException;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];

    private Type mType;

    private ParserConfig config;
    private int featureValues;
    private Feature[] features;


    FastJsonResponseBodyConverter(Type type, ParserConfig config, int featureValues, Feature... features) {
        mType = type;
        this.config = config;
        this.featureValues = featureValues;
        this.features = features;
    }


    @SuppressWarnings("unchecked")
    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String string = value.string();
            JSONObject jsonObject = JSON.parseObject(string);
            String code = jsonObject.getString("retCode");

            if (code == null && !jsonObject.containsKey("result")) {
                return JSON.parseObject(string, mType, config, featureValues,
                        features != null ? features : EMPTY_SERIALIZER_FEATURES);
            }
            //目前调用的网易易盾API公共参数也是code和msg
            //如果后期接入其他第三方API再根据返回参数修改code逻辑
            if (code.equals("200")) {
                if (mType == String.class) {
                    return (T) jsonObject.getString("result");
                } else if (mType == Boolean.class) {
                    return (T) jsonObject.getBoolean("result");
                } else if (mType == Integer.class) {
                    return (T) jsonObject.getInteger("result");
                } else if (mType == Double.class) {
                    return (T) jsonObject.getDouble("result");
                } else if (mType == Float.class) {
                    return (T) jsonObject.getFloat("result");
                } else if (mType == Long.class) {
                    return (T) jsonObject.getLong("result");
                } else if (mType == Object.class) {
                    return (T) jsonObject.get("result");
                } else {
                    String data = jsonObject.getString("result");
                    if (TextUtils.isEmpty(data)) {
                        //调用第三方API时，如果data字段为空，则解析完整的json数据
                        return JSON.parseObject(string, mType, config, featureValues,
                                features != null ? features : EMPTY_SERIALIZER_FEATURES);
                    } else {
                        //自己的API，只解析data数据
                        return JSON.parseObject(data, mType, config, featureValues,
                                features != null ? features : EMPTY_SERIALIZER_FEATURES);
                    }
                }
            }else{
                throw new ApiException(code, "");
            }
        } finally {
            value.close();
        }
    }
}
