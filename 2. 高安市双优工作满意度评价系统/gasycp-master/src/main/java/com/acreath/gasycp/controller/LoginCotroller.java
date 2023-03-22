package com.acreath.gasycp.controller;

import com.acreath.gasycp.enumeration.BaseCode;
import com.acreath.gasycp.exception.BaseException;
import com.acreath.gasycp.mapper.UserMapper;
import com.acreath.gasycp.po.LoginUserPO;
import com.acreath.gasycp.service.UserService;
import com.acreath.gasycp.util.ResultBuilderUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: longteng
 * @date: 2018/10/23 12:57
 * @description:
 */

@Controller
@RequestMapping("/user")
public class LoginCotroller {

/*    @Autowired
    private SessionRepository<?> sessionRepository;*/

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    protected static final String   JSON_UTF8 = "application/json;charset=UTF-8";

    @RequestMapping(value = {"/", "/login.html", "/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        if (userService.isLogin(request)) {
            return "redirect:/index";
        }
        return "login";
    }
    @RequestMapping(value = { "/homePhone.html", "/homePhone"}, method = RequestMethod.GET)
    public String loginPhone(HttpServletRequest request) {
        if (userService.isLogin(request)) {
            return "redirect:/index";
        }
        return "home_phone";
    }

    @ResponseBody
    @RequestMapping(value = "/login/authenticate", method = RequestMethod.POST, produces = JSON_UTF8)
    public String authenticate(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,HttpServletRequest request){

        if (StringUtils.isEmpty(username)) {
            return ResultBuilderUtils.buildError("请输入登录名");
        }

        if (StringUtils.isEmpty(password)) {
            return ResultBuilderUtils.buildError("请输入密码");
        }
        return userService.login(request, username, password);
    }

/*
    private void clearSession(HttpServletRequest request){
        String sessionId = request.getSession().getId();
        request.getSession().invalidate();
        sessionRepository.delete(sessionId);
    }*/


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        //clearSession(request);

        return "redirect:/login";
    }

    @RequestMapping(value = "/fixPassword", method = RequestMethod.GET, produces = JSON_UTF8)
    public  String fixPassword(){
        return "change_password";
    }


    @ResponseBody
    @RequestMapping(value = "/editPassword", method = RequestMethod.POST ,produces = JSON_UTF8)
    public String editPassword(@RequestParam(value = "username") String username, @RequestParam(value = "original_password") String password ,@RequestParam(value = "new_password") String newPassword){
        LoginUserPO loginUserPO = userMapper.getUserAccountByLoginNameAndPassword(username,password);
        if(userMapper.searchAccount(username) == null){
            throw new BaseException(BaseCode.INVALID_ARGUMENT,"用户不存在");
        }
        if (loginUserPO == null)
        {
            throw new BaseException(BaseCode.INVALID_ARGUMENT,"原密码错误");
        }
        userService.updatePassword(username ,newPassword);
        return ResultBuilderUtils.buildSuccess("SUCCESS");
    }


}
