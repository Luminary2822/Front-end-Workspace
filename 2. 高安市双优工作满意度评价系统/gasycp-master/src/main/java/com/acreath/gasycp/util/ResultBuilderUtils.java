package com.acreath.gasycp.util;

import com.acreath.gasycp.dto.BaseResponseDTO;
import com.acreath.gasycp.enumeration.BaseCode;
import com.alibaba.fastjson.JSONObject;

/**
 * @author: longteng
 * @date: 2018/10/23 13:59
 * @description:
 */public class ResultBuilderUtils {
    public static String buildSuccess(String message) {
        JSONObject result = new JSONObject();
        result.put("message", message);
        return buildSuccess(result);
    }

    public static String buildSuccess(Object data) {
        BaseResponseDTO result = new BaseResponseDTO(data);
        return FastJsonUtil.toJSONString(result);
    }

    public static String buildError(String message) {
        BaseResponseDTO result = new BaseResponseDTO(message);
        return FastJsonUtil.toJSONString(result);
    }

    public static String buildError(BaseCode errorCode) {
        BaseResponseDTO result = new BaseResponseDTO(errorCode.getCode(), errorCode.getMessage());
        return FastJsonUtil.toJSONString(result);
    }

    public static String buildError(BaseCode errorCode, String errorMsg) {
        BaseResponseDTO result = new BaseResponseDTO(errorCode.getCode(), errorCode.getMessage(), errorMsg);
        return FastJsonUtil.toJSONString(result);
    }

    public static String buildErrorWithData(BaseCode errorCode, Object data) {
        BaseResponseDTO result = new BaseResponseDTO(errorCode.getCode(), errorCode.getMessage(), errorCode.getMessage(), data);
        return FastJsonUtil.toJSONString(result);
    }
}