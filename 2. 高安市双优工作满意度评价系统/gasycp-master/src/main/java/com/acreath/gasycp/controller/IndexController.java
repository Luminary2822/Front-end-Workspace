package com.acreath.gasycp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: longteng
 * @date: 2018/10/24 15:09
 * @description:
 */
@Controller
@RequestMapping("/")
public class IndexController  {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "home.html";
    }
}
