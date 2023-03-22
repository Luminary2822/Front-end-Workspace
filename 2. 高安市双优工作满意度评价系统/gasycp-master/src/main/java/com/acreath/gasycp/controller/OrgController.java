package com.acreath.gasycp.controller;

import com.acreath.gasycp.dto.*;

import com.acreath.gasycp.mapper.MassQuestionMapper;
import com.acreath.gasycp.mapper.OrgFilesMapper;
import com.acreath.gasycp.mapper.OrgMapper;
import com.acreath.gasycp.mapper.UserMapper;
import com.acreath.gasycp.service.OrgService;
import com.acreath.gasycp.util.FastJsonUtil;
import com.acreath.gasycp.util.ResultBuilderUtils;
import com.acreath.gasycp.util.ThreadLocalUtils;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/12 22:23
 * @description:
 */

@Controller
@RequestMapping("/org")
public class OrgController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
     private OrgService orgService;
    @Autowired
    private OrgFilesMapper orgFilesMapper;
    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private MassQuestionMapper massQuestionMapper;


    @RequestMapping(value = "/index" ,method = RequestMethod.GET)
    public String index (Model model , HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("org_id" ) == null){
            return "login";

        }
        int orgId =  (int)session.getAttribute("org_id");
        model.addAttribute("orgInfo", JSON.toJSON(orgService.getOrgInfo(orgId)));
        return "unit_page";
    }

    @RequestMapping(value = "/OrgFilesTable" ,method = RequestMethod.POST)
    @ResponseBody
    public String filesTable(Model model, HttpServletRequest request, @RequestBody String payload){
        OrgFileTableDTO orgFileTableDTO = FastJsonUtil.toBean(payload,OrgFileTableDTO.class);
        List<OrgFilesDTO> orgFilesDTOS = orgFilesMapper.getOrgFilesByOrgId(orgFileTableDTO.getOrgId());
        BootstrapTableDTO bootstrapTableDTO = new BootstrapTableDTO();
        bootstrapTableDTO.setTotal(orgFilesDTOS.size());
        bootstrapTableDTO.setRows(orgService.getFileType(orgFilesDTOS));
        return FastJsonUtil.toJSONString(bootstrapTableDTO);
    }

    @RequestMapping(value = "/deletedFile", method = RequestMethod.POST)
    @ResponseBody
    public String deletedFile(@RequestParam("id") int id,@RequestParam("one_type") String orgType,@RequestParam("org_id") int orgId){
       orgType = orgFilesMapper.getFileById(id).getOneType();
        orgFilesMapper.deletedFile(id);
        switch (orgType) {
            case "TYPE_ONE_MODULE1":
                orgMapper.upDataModule1FileStatus(0,orgId);
                break;
            case "TYPE_ONE_MODULE2":
                orgMapper.upDataModule2FileStatus(0,orgId);
                break;
            case "TYPE_ONE_MODULE3":
                orgMapper.upDataModule3FileStatus(0,orgId);
                break;
            case "TYPE_ONE_MODULE4":
                orgMapper.upDataModule4FileStatus(0,orgId);
                break;
            case "TYPE_ONE_MODULE5":
                orgMapper.upDataModule5FileStatus(0,orgId);
                break;
            case "TYPE_ONE_MODULE6":
                orgMapper.upDataModule6FileStatus(0,orgId);
                break;
            default:
                break;
        }
        return ResultBuilderUtils.buildSuccess(id);
    }

    @RequestMapping(value = "/leaderList",method = RequestMethod.GET)
    public String leaderList (Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("identitys" ) == null){
            return "login";
        }
        int accountId = (int)session.getAttribute("account_id");
        model.addAttribute("account_id",accountId);
        model.addAttribute("score_type",2);
        model.addAttribute("is_score",userMapper.searchAccountById(accountId).getIsScore());


        List<OrgInfoDTO> orgType1 = orgMapper.getOrgByType(1);
        List<OrgInfoDTO> orgType2 = orgMapper.getOrgByType(2);
        List<OrgInfoDTO> orgType3 = orgMapper.getOrgByType(3);
        List<OrgInfoDTO> orgType4 = orgMapper.getOrgByType(4);
        List<OrgInfoDTO> orgType5 = orgMapper.getOrgByType(5);

        model.addAttribute("orgType1",JSON.toJSON(orgType1));
        model.addAttribute("orgType2",JSON.toJSON(orgType2));
        model.addAttribute("orgType3",JSON.toJSON(orgType3));
        model.addAttribute("orgType4",JSON.toJSON(orgType4));
        model.addAttribute("orgType5",JSON.toJSON(orgType5));

        return "leadership_score";
    }

    @RequestMapping(value = "/{orgId:[\\d]+}.html",method = RequestMethod.GET)
    public String orgView(@PathVariable int orgId, Model model ,HttpServletRequest request){
        HttpSession session = request.getSession();

        if (session.getAttribute("identitys" ) == null){
            return "login";
        }
        List<OrgFilesDTO> orgFilesDTOS = orgFilesMapper.getOrgFilesByOrgId(orgId);
        FilesUrlDTO filesUrlDTO = new FilesUrlDTO();
        for (OrgFilesDTO orgFilesDTO :orgFilesDTOS){
            switch (orgFilesDTO.getOneType()){
                 case "TYPE_ONE_MODULE1":
                     filesUrlDTO.setTypeOneModule1(orgFilesDTO.getFileUrl());
                        break;
                    case "TYPE_ONE_MODULE2":
                        filesUrlDTO.setTypeOneModule2(orgFilesDTO.getFileUrl());
                        break;
                    case "TYPE_ONE_MODULE3":
                        filesUrlDTO.setTypeOneModule3(orgFilesDTO.getFileUrl());
                        break;
                    case "TYPE_ONE_MODULE4":
                        filesUrlDTO.setTypeOneModule4(orgFilesDTO.getFileUrl());
                        break;
                    case "TYPE_ONE_MODULE5":
                        filesUrlDTO.setTypeOneModule5(orgFilesDTO.getFileUrl());
                        break;
                    case "TYPE_ONE_MODULE6":
                        filesUrlDTO.setTypeOneModule6(orgFilesDTO.getFileUrl());
                        break;
                    default:
                        break;
            }
        }



        int identitys = (int)session.getAttribute("identitys");
        OrgInfoDTO orgInfoDTO = orgService.getOrgInfo(orgId);
        model.addAttribute("orgInfo",JSON.toJSON(orgInfoDTO));
        int accountId =(int) session.getAttribute("account_id");
        model.addAttribute("account_id",accountId);
        model.addAttribute("filesUrl",JSON.toJSON(filesUrlDTO));
        if (identitys == 2) {
            return "leadership_view";
        }
        if (identitys == 3){
            return "expert_view";
        }
        return null;
    }

    @RequestMapping(value = "/phone{orgId:[\\d]+}.html",method = RequestMethod.GET)
    public String orgPhoneView(@PathVariable int orgId, Model model ,HttpServletRequest request){
        HttpSession session = request.getSession();

        if (session.getAttribute("identitys" ) == null){
            return "login";
        }
        List<OrgFilesDTO> orgFilesDTOS = orgFilesMapper.getOrgFilesByOrgId(orgId);
        FilesUrlDTO filesUrlDTO = new FilesUrlDTO();
        for (OrgFilesDTO orgFilesDTO :orgFilesDTOS){
            switch (orgFilesDTO.getOneType()){
                case "TYPE_ONE_MODULE1":
                    filesUrlDTO.setTypeOneModule1(orgFilesDTO.getFileUrl());
                    break;
                case "TYPE_ONE_MODULE2":
                    filesUrlDTO.setTypeOneModule2(orgFilesDTO.getFileUrl());
                    break;
                case "TYPE_ONE_MODULE3":
                    filesUrlDTO.setTypeOneModule3(orgFilesDTO.getFileUrl());
                    break;
                case "TYPE_ONE_MODULE4":
                    filesUrlDTO.setTypeOneModule4(orgFilesDTO.getFileUrl());
                    break;
                case "TYPE_ONE_MODULE5":
                    filesUrlDTO.setTypeOneModule5(orgFilesDTO.getFileUrl());
                    break;
                case "TYPE_ONE_MODULE6":
                    filesUrlDTO.setTypeOneModule6(orgFilesDTO.getFileUrl());
                    break;
                default:
                    break;
            }
        }



        int identitys = (int)session.getAttribute("identitys");
        OrgInfoDTO orgInfoDTO = orgService.getOrgInfo(orgId);
        model.addAttribute("orgInfo",JSON.toJSON(orgInfoDTO));
        int accountId =(int) session.getAttribute("account_id");
        model.addAttribute("account_id",accountId);
        model.addAttribute("filesUrl",JSON.toJSON(filesUrlDTO));
        if (identitys == 2) {
            return "leadership_view_phone";
        }
        if (identitys == 3){
            return "expert_view_phone";
        }
        return null;
    }

    @RequestMapping(value = "/expertList" , method = RequestMethod.GET)
    public String expertList (Model model,HttpServletRequest request){

        HttpSession session = request.getSession();
        if (session.getAttribute("identitys" ) == null){
            return "login";
        }

        List<OrgInfoDTO> orgType1 = orgMapper.getOrgByType(1);
        List<OrgInfoDTO> orgType2 = orgMapper.getOrgByType(2);
        List<OrgInfoDTO> orgType3 = orgMapper.getOrgByType(3);
        List<OrgInfoDTO> orgType4 = orgMapper.getOrgByType(4);
        List<OrgInfoDTO> orgType5 = orgMapper.getOrgByType(5);

        model.addAttribute("orgType1",JSON.toJSON(orgType1));
        model.addAttribute("orgType2",JSON.toJSON(orgType2));
        model.addAttribute("orgType3",JSON.toJSON(orgType3));
        model.addAttribute("orgType4",JSON.toJSON(orgType4));
        model.addAttribute("orgType5",JSON.toJSON(orgType5));
        return "expert_score";
    }
/*
    @RequestMapping(value = "/{orgId:[\\d]+}.html",method = RequestMethod.GET)
    public String expertOrgView(@PathVariable int orgId, Model model){
        OrgInfoDTO orgInfoDTO = orgService.getOrgInfo(orgId);
        model.addAttribute("orgInfo",JSON.toJSON(orgInfoDTO));
        return "expert_view" ;
    }*/

    @RequestMapping(value = "/adminList" , method = RequestMethod.GET)
    public String adminList (Model model,HttpServletRequest request){

        HttpSession session = request.getSession();
        model.addAttribute("account_id",session.getAttribute("account_id"));
        model.addAttribute("identitys",(int)session.getAttribute("identitys"));
        if (session.getAttribute("identitys" ) == null){
            return "login";
        }


        List<OrgInfoDTO> orgType1 = orgMapper.getOrgByType(1);
        List<OrgInfoDTO> orgType2 = orgMapper.getOrgByType(2);
        List<OrgInfoDTO> orgType3 = orgMapper.getOrgByType(3);
        List<OrgInfoDTO> orgType4 = orgMapper.getOrgByType(4);
        List<OrgInfoDTO> orgType5 = orgMapper.getOrgByType(5);

        model.addAttribute("orgType1",JSON.toJSON(orgType1));
        model.addAttribute("orgType2",JSON.toJSON(orgType2));
        model.addAttribute("orgType3",JSON.toJSON(orgType3));
        model.addAttribute("orgType4",JSON.toJSON(orgType4));
        model.addAttribute("orgType5",JSON.toJSON(orgType5));
        return "administrative_score";
    }


    @RequestMapping(value = "/townList" , method = RequestMethod.GET)
    public String townList (Model model,HttpServletRequest request){

        HttpSession session = request.getSession();
        if (session.getAttribute("identitys" ) == null){
            return "login";
        }
        int orgId = (int)session.getAttribute("org_id");
        int accountId = (int)session.getAttribute("account_id");
        model.addAttribute("account_id",accountId);
        model.addAttribute("is_score",userMapper.searchAccountById(accountId).getIsScore());
        OrgInfoDTO orgInfoDTO = orgMapper.findOrgByOrgId(orgId);
        List<OrgInfoDTO> orgType1 = new ArrayList<>();
        orgType1.add(orgInfoDTO);
        List<OrgInfoDTO> orgType2 = orgMapper.getOrgByType(2);
        List<OrgInfoDTO> orgType3 = orgMapper.getOrgByType(3);
        List<OrgInfoDTO> orgType4 = orgMapper.getOrgByType(4);
        List<OrgInfoDTO> orgType5 = orgMapper.getOrgByType(5);
        shuffle(orgType2);
        shuffle(orgType3);
        shuffle(orgType4);
        shuffle(orgType5);

        model.addAttribute("orgType1",JSON.toJSON(orgType1));
        model.addAttribute("orgType2",JSON.toJSON(orgType2));
        model.addAttribute("orgType3",JSON.toJSON(orgType3));
        model.addAttribute("orgType4",JSON.toJSON(orgType4));
        model.addAttribute("orgType5",JSON.toJSON(orgType5));
        return "town_enterprise_score";
    }

    @RequestMapping(value = "/areaList" , method = RequestMethod.GET)
    public String areaList (Model model,HttpServletRequest request){

        HttpSession session = request.getSession();
        int accountId = (int)session.getAttribute("account_id");
        model.addAttribute("account_id",accountId);
        model.addAttribute("is_score",userMapper.searchAccountById(accountId).getIsScore());

        if (session.getAttribute("identitys" ) == null){
            return "login";
        }



        List<OrgInfoDTO> orgType2 = orgMapper.getOrgByType(2);
        List<OrgInfoDTO> orgType3 = orgMapper.getOrgByType(3);
        List<OrgInfoDTO> orgType4 = orgMapper.getOrgByType(4);
        List<OrgInfoDTO> orgType5 = orgMapper.getOrgByType(5);

        model.addAttribute("orgType2",JSON.toJSON(orgType2));
        model.addAttribute("orgType3",JSON.toJSON(orgType3));
        model.addAttribute("orgType4",JSON.toJSON(orgType4));
        model.addAttribute("orgType5",JSON.toJSON(orgType5));
        return "area_enterprise_score";
    }

    @RequestMapping(value = "/massPhone" ,method = RequestMethod.POST)
    @ResponseBody
    public String massPhone (Model model , HttpServletRequest request){
        String ip = request.getRemoteAddr();
        int markPhone =  massQuestionMapper.contMassQuestionByPhone(ip);
        if (markPhone == 0 )
        {
            return  ResultBuilderUtils.buildSuccess("进入评价");
        }
        ThreadLocalUtils.setphone(ip);
        return ResultBuilderUtils.buildError(ip+"已评价");

    }

    @RequestMapping(value = "/massQuestion", method = RequestMethod.POST)
    @ResponseBody
    public String massQuestion(Model model , HttpServletRequest request, @RequestParam("check_val[]") List<String> checkVal, @RequestParam("check_val2[]") List<String> checkVal2 ,@RequestParam("text") String text1 ){
      List<MassQuestionDTO> massQuestionDTOS =new ArrayList<>();
        String phone = request.getRemoteAddr();
        int markPhone =  massQuestionMapper.contMassQuestionByPhone(phone);
        if (markPhone == 0 ) {
            for (String orgName : checkVal) {
                MassQuestionDTO massQuestionDTO = new MassQuestionDTO();
                massQuestionDTO.setOrgName(orgName);
                massQuestionDTO.setIsGood(1);
                massQuestionDTO.setPhone(phone);
                massQuestionDTOS.add(massQuestionDTO);
            }
            for (String orgName : checkVal2) {
                MassQuestionDTO massQuestionDTO = new MassQuestionDTO();
                massQuestionDTO.setOrgName(orgName);
                massQuestionDTO.setIsGood(0);
                massQuestionDTO.setPhone(phone);
                massQuestionDTOS.add(massQuestionDTO);
            }
            massQuestionMapper.insertMassQuestions(massQuestionDTOS);
            return ResultBuilderUtils.buildSuccess("SUCCESS");
        }
        else
        {
            return ResultBuilderUtils.buildError("已评价");
        }
    }

    @RequestMapping(value = "/getMassQuestion" ,method = RequestMethod.GET)
    public String getMassQuestion()
    {

        int i = (int)(1+Math.random()*(10-1+1));
        return "mass_questionnaire"+i;
    }

    public <T> void shuffle(List<T> list) {
        // 打乱顺序
        Collections.shuffle(list);
    }
    @RequestMapping(value = "/adminPost" , method = RequestMethod.POST)
    @ResponseBody
    public String adminPost (Model model,HttpServletRequest request,@RequestParam("orgType") int id){
        List<OrgInfoDTO> orgType = orgMapper.getOrgByType(id);
        return ResultBuilderUtils.buildSuccess(orgType);
    }




}
