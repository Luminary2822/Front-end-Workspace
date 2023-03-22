package com.acreath.gasycp.exception;

import com.acreath.gasycp.enumeration.BaseCode;
import com.acreath.gasycp.util.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: longteng
 * @date: 2018/10/23 13:47
 * @description:
 */public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final BaseCode error;

    public BaseException(BaseCode ec, String message, Throwable cause) {
        super(message, cause);
        error = ec;
    }

    public BaseException(BaseCode ec) {
        this(ec, ec.getMessage(), null);
    }

    public BaseException(BaseCode ec, String message) {
        this(ec, message, null);
    }

    public BaseException(BaseCode ec, Throwable cause) {
        this(ec, null, cause);
    }

    public BaseCode getError() {
        return error;
    }

    @Override
    public String toString() {
        Map<String, String> jsonObj = new HashMap<String, String>();
        jsonObj.put("code", String.valueOf(error.getCode()));
        jsonObj.put("error_msg", error.getMessage()+","+this.getMessage());

        return FastJsonUtil.toJSONNoFeatures(jsonObj);

    }
}
