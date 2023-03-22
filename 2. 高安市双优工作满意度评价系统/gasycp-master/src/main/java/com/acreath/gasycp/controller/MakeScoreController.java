package com.acreath.gasycp.controller;

import com.acreath.gasycp.dto.MakeScoreDTO;
import com.acreath.gasycp.mapper.MakeScoreMapper;
import com.acreath.gasycp.mapper.OrgMapper;
import com.acreath.gasycp.mapper.OrgQuestionMapper;
import com.acreath.gasycp.mapper.UserMapper;
import com.acreath.gasycp.service.OrgService;
import com.acreath.gasycp.util.FastJsonUtil;
import com.acreath.gasycp.util.ResultBuilderUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.acreath.gasycp.controller.LoginCotroller.JSON_UTF8;

/**
 * @author: longteng
 * @date: 2018/11/27 19:22
 * @description:
 */
@Controller
@EnableAsync
@RequestMapping(value = "/makeScore")
public class MakeScoreController {
    @Autowired
    private OrgQuestionMapper orgQuestionMapper;
    @Autowired
    private OrgService orgService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MakeScoreMapper makeScoreMapper;
    @RequestMapping(value = "/addScore" , method = RequestMethod.POST ,produces = JSON_UTF8)
    @ResponseBody
    @Async
    public String addScore(@RequestParam(name = "lead_score[]") List<Integer> leadScore,@RequestParam(name = "org_id[]") List<Integer> orgId, @RequestParam(name = "score_type") int scoreType,@RequestParam(name = "account_id") int accountId ){
        long startTime = System.currentTimeMillis();
        List<MakeScoreDTO> makeScoreDTOS = new ArrayList<>();
        for (int i = 0 ;i<leadScore.size();i++)
        {
            MakeScoreDTO makeScoreDTO = new MakeScoreDTO();
            if (leadScore.get(i) == null)
            {
                makeScoreDTO.setScore(0);
            }
            else
            {
                makeScoreDTO.setScore(leadScore.get(i));
            }
            makeScoreDTO.setAccountId(accountId);
            makeScoreDTO.setScoreType(scoreType);
            makeScoreDTO.setOrgId(orgId.get(i));
            makeScoreDTOS.add(makeScoreDTO);

        }

        for (int i : orgId)
        {
            orgService.upDataOrgStatus(1,i);
            orgService.upDataOrgStatus(2,i);
            orgService.upDataOrgStatus(3,i);
        }

        makeScoreMapper.addScore(makeScoreDTOS);
        userMapper.upDateIsScore(accountId);
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return ResultBuilderUtils.buildSuccess("SUCCESS");
    }

    @RequestMapping(value = "/addExpertScore" , method = RequestMethod.POST ,produces = JSON_UTF8)
    @ResponseBody

    public String addExpertScore(@RequestParam(name = "lead_score[]") List<Integer> leadScore,@RequestParam(name = "org_id[]") List<Integer> orgId, @RequestParam(name = "score_type") int scoreType,@RequestParam(name = "account_id") int accountId ){
        List<MakeScoreDTO> makeScoreDTOS = new ArrayList<>();
        for (int i = 0 ;i<leadScore.size();i++)
        {
            makeScoreMapper.deleteOne(accountId,orgId.get(i));
            MakeScoreDTO makeScoreDTO = new MakeScoreDTO();
            if (leadScore.get(i) == null)
            {
                makeScoreDTO.setScore(0);
            }
            else
            {
                makeScoreDTO.setScore(leadScore.get(i));
            }
            makeScoreDTO.setAccountId(accountId);
            makeScoreDTO.setScoreType(scoreType);
            makeScoreDTO.setOrgId(orgId.get(i));
            makeScoreDTOS.add(makeScoreDTO);
        }
        for (int i : orgId)
        {
            orgService.upDataOrgStatus(1,i);
            orgService.upDataOrgStatus(2,i);
            orgService.upDataOrgStatus(3,i);
        }
        makeScoreMapper.addScore(makeScoreDTOS);
        makeScoreMapper.expertAddScore(makeScoreDTOS);
        int mark = makeScoreMapper.contScoreByAccount(accountId);
        if ( mark == 138)
        {
            userMapper.upDateIsScore(accountId);
        }
        return  ResultBuilderUtils.buildSuccess("SUCCESS");
    }


    @RequestMapping(value = "/addOrgScore" , method = RequestMethod.POST ,produces = JSON_UTF8)
    @ResponseBody
    @Async
    public synchronized String addOrgScore(@RequestParam(name = "lead_score[]") List<Integer> leadScore,@RequestParam(name = "org_id[]") List<Integer> orgId, @RequestParam(name = "score_type") int scoreType,@RequestParam(name = "account_id") int accountId, @RequestParam(name ="check_sumval") String payload ){
        List<String[]> strings = FastJsonUtil.toList(payload,String[].class);

        long startTime = System.currentTimeMillis();
        int mark = 1;
        for (String[] strings1 : strings)
        {
            for (String aStrings1 : strings1) {
                orgQuestionMapper.insertOrgQuestions(accountId, aStrings1,mark);
            }
            mark++;

        }

        List<MakeScoreDTO> makeScoreDTOS = new ArrayList<>();
        makeScoreMapper.deleteAll(accountId);
        for (int i = 0 ;i<leadScore.size();i++)
        {
            MakeScoreDTO makeScoreDTO = new MakeScoreDTO();
            if (leadScore.get(i) == null)
            {
                makeScoreDTO.setScore(0);
            }
            else
            {
                makeScoreDTO.setScore(leadScore.get(i));
            }
            makeScoreDTO.setAccountId(accountId);
            makeScoreDTO.setScoreType(scoreType);
            makeScoreDTO.setOrgId(orgId.get(i));
            makeScoreDTOS.add(makeScoreDTO);

        }

        for (int i : orgId)
        {
            orgService.upDataOrgStatus(1,i);
            orgService.upDataOrgStatus(2,i);
            orgService.upDataOrgStatus(3,i);
        }

        makeScoreMapper.addScore(makeScoreDTOS);
        userMapper.upDateIsScore(accountId);
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return ResultBuilderUtils.buildSuccess("SUCCESS");
    }


}
