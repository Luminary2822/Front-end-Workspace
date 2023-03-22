package com.acreath.gasycp.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: longteng
 * @date: 2018/10/23 12:58
 * @description:
 */
public interface UserService {
    boolean isLogin(HttpServletRequest request);
    String login(HttpServletRequest request, String loginName, String password);
    String updatePassword(String username, String password);
}
