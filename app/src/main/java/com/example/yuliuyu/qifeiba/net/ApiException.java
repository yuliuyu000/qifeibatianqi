package com.example.yuliuyu.qifeiba.net;

import android.support.annotation.Nullable;

/**
 * 项目名称：Android
 * 类描述：服务器返回错误
 * 创建人：LiuJun
 * 创建时间：16/8/25 11:02
 * 修改人：LiuJun
 * 修改时间：16/8/25 11:02
 * 修改备注：
 */
public class ApiException extends RuntimeException {

    private String mCode;
    private String mErrMsg;


    public ApiException(String code, String message) {
        super("retCode: " + code + "  msg: " + message);
        mCode = code;
        mErrMsg = message;
    }


    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     */
    @Nullable
    private static String getApiExceptionMessage(String code, String msg) {
        String message;
        switch (code) {
            default:
                message = msg;
        }
        return message;
    }


    public String getCode() {
        return mCode;
    }


    public String getErrMsg() {
        return getApiExceptionMessage(mCode, mErrMsg);
    }
}
