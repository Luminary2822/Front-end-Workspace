package com.acreath.gasycp.service;

import com.acreath.gasycp.dto.LoginUserDTO;
import com.acreath.gasycp.enumeration.BaseCode;
import com.acreath.gasycp.exception.BaseException;
import com.acreath.gasycp.mapper.UserMapper;
import com.acreath.gasycp.po.LoginUserPO;
import com.acreath.gasycp.util.ResultBuilderUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: longteng
 * @date: 2018/10/23 12:59
 * @description:
 */

@EnableAsync
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginUserDTO loginUser = (LoginUserDTO) session.getAttribute("login_user_info");
        return loginUser != null;
    }

    @Override
    public String login(HttpServletRequest request, String username, String password) {
        LoginUserPO loginUserPO = getLoginUserInfo(username,password);
        if (null == loginUserPO) {
            logger.error("用户{}登录，用户名或密码错误，密码：{}", username, password);
            throw new BaseException(BaseCode.INVALID_ARGUMENT, "用户名或密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginUser",loginUserPO);
        session.setAttribute("login_name",loginUserPO.getLoginName());
        session.setAttribute("identitys" ,loginUserPO.getIdentitys());
        session.setAttribute("account_id",loginUserPO.getId());
        if (loginUserPO.getOrgId() != 0) {
            session.setAttribute("org_id",loginUserPO.getOrgId());
        }


        return  ResultBuilderUtils.buildSuccess(loginUserPO.getIdentitys());
    }

    @Override
    public String updatePassword(String username, String newPassword) {
        if (userMapper.searchAccount(username) == null){
            logger.error("不存在该账号");
            throw new BaseException(BaseCode.INVALID_ARGUMENT,"不存在该账号");
        }
        userMapper.updatePassword(username, newPassword);
        return username;
    }

    private LoginUserPO getLoginUserInfo(String username, String password) {
        LoginUserPO loginUserPO = userMapper.getUserAccountByLoginNameAndPassword(username,password);
        if (null == loginUserPO) {
            return null;
        }
        return loginUserPO;
    }
}