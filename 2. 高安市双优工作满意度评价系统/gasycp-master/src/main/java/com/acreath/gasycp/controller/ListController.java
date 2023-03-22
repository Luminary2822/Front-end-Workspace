package com.acreath.gasycp.controller;

import com.acreath.gasycp.dto.AdminListDTO;
import com.acreath.gasycp.dto.OrgInfoDTO;
import com.acreath.gasycp.mapper.OrgMapper;
import com.acreath.gasycp.mapper.UserMapper;
import com.acreath.gasycp.po.LoginUserPO;
import com.acreath.gasycp.service.OrgService;
import com.acreath.gasycp.util.FastJsonUtil;
import com.acreath.gasycp.util.ResultBuilderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: longteng
 * @date: 2018/12/8 20:53
 * @description:
 */

@Controller
@RequestMapping(value = "/testList")
public class ListController {

    @Autowired
    private OrgService orgService;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/getTestList" ,method = RequestMethod.POST)
    @ResponseBody
    public String getList(@RequestBody String payload){
      AdminListDTO adminListDTO = FastJsonUtil.toBean(payload, AdminListDTO.class);
        switch (adminListDTO.getRole()){
            case "expert":
                return ResultBuilderUtils.buildSuccess(orgService.fixIsScore(userMapper.allByType(3),adminListDTO.getOrgId()));
            case "leader":
                return ResultBuilderUtils.buildSuccess(userMapper.allByType(2));
            case "adm":
               OrgInfoDTO orgInfoDTO = orgMapper.findOrgByOrgId(adminListDTO.getOrgId());
               if (orgInfoDTO.getOrgType()!=1){
                return ResultBuilderUtils.buildSuccess(userMapper.allByType(6));
               }
               if (orgInfoDTO.getOrgType() ==1 ){
                   List<LoginUserPO> loginUserPOS = userMapper.searchAccountByLoginNameList(orgInfoDTO.getOrgName());
                   return  ResultBuilderUtils.buildSuccess(loginUserPOS);
               }

            case "module":
                List<OrgInfoDTO> orgInfoDTOList  = new ArrayList<>();
                orgInfoDTOList.add(orgMapper.findOrgByOrgId(adminListDTO.getOrgId()));
                return ResultBuilderUtils.buildSuccess(orgInfoDTOList);
            default:
                return  null;
        }
    }

}
