package com.acreath.gasycp.util;

/**
 * @author: longteng
 * @date: 2018/11/30 00:38
 * @description:
 *
 */public class ThreadLocalUtils {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

    public static void setphone(String phone) {
        threadLocal.set(phone);
    }

    public static String phone() {
        return (String) threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
