package com.acreath.gasycp.controller;

import com.acreath.gasycp.dto.OrgInfoDTO;
import com.acreath.gasycp.mapper.OrgMapper;
import com.acreath.gasycp.mapper.UserMapper;
import com.acreath.gasycp.service.OrgService;
import com.acreath.gasycp.util.FastJsonUtil;
import com.acreath.gasycp.util.ResultBuilderUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/28 16:39
 * @description:
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private OrgService orgService;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "/adminList" , method = RequestMethod.GET)
    public String adminList(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("identitys" ) == null){
            return "login";
        }
        List<OrgInfoDTO> orgType1 = orgMapper.getOrgByType(1);
        List<OrgInfoDTO> orgType2 = orgMapper.getOrgByType(2);
        List<OrgInfoDTO> orgType3 = orgMapper.getOrgByType(3);
        List<OrgInfoDTO> orgType4 = orgMapper.getOrgByType(4);
        List<OrgInfoDTO> orgType5 = orgMapper.getOrgByType(5);


        model.addAttribute("orgType1", JSON.toJSON(orgType1));
        model.addAttribute("orgType2",JSON.toJSON(orgType2));
        model.addAttribute("orgType3",JSON.toJSON(orgType3));
        model.addAttribute("orgType4",JSON.toJSON(orgType4));
        model.addAttribute("orgType5",JSON.toJSON(orgType5));


        return "manage_list";
    }

    @RequestMapping(value = {"/manage_detail","/manage_detail.html" }, method = RequestMethod.GET)
    public String adminListDetaile(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("identitys" ) == null){
            return "login";
        }

        return "manage_detail";
    }



    @RequestMapping(value = "/getList" ,method = RequestMethod.POST)
    @ResponseBody
    public String getList( @RequestParam("org_id") int orgId,@RequestParam("role") String role){
        switch (role){
            case "expert":
            return ResultBuilderUtils.buildSuccess(orgService.fixIsScore(userMapper.allByType(3),orgId));
            case "leader":
                return ResultBuilderUtils.buildSuccess(userMapper.allByType(2));
            case "adm":
                return ResultBuilderUtils.buildSuccess(userMapper.admByType(6,7));
            case "module":
                return ResultBuilderUtils.buildSuccess(orgMapper.findOrgByOrgId(orgId));
                default:
                    return  null;
        }
    }

    @RequestMapping(value = "/getList2" ,method = RequestMethod.POST)
    @ResponseBody
    public String getListTwo( @RequestParam("org_id") int orgId,@RequestParam("role") String role){
        switch (role){
            case "expert":
                return ResultBuilderUtils.buildSuccess(orgService.fixIsScore(userMapper.allByType(3),orgId));
            case "leader":
                return ResultBuilderUtils.buildSuccess(userMapper.allByType(2));
            case "adm":
                return ResultBuilderUtils.buildSuccess(userMapper.admByType(6,7));
            case "module":
                return ResultBuilderUtils.buildSuccess(orgMapper.findOrgByOrgId(orgId));
            default:
                return  null;
        }
    }

}
