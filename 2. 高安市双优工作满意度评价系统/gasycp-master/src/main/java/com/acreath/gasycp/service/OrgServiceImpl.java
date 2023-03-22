package com.acreath.gasycp.service;

import com.acreath.gasycp.dto.OrgFilesDTO;
import com.acreath.gasycp.dto.OrgInfoDTO;
import com.acreath.gasycp.enumeration.FileType;
import com.acreath.gasycp.mapper.MakeScoreMapper;
import com.acreath.gasycp.mapper.OrgMapper;
import com.acreath.gasycp.mapper.UserMapper;
import com.acreath.gasycp.po.LoginUserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/13 13:29
 * @description:test
 */
@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
  private  OrgMapper orgMapper;
    @Autowired
    private MakeScoreMapper makeScoreMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public OrgInfoDTO getOrgInfo(int orgId) {

        return orgMapper.findOrgByOrgId(orgId) ;
    }

    @Override
    public List<OrgFilesDTO> getFileType(List<OrgFilesDTO> list) {
        for (OrgFilesDTO orgFilesDTO: list) {
            if (orgFilesDTO.getOneType() != null) {
                switch (orgFilesDTO.getOneType()) {
                    case "TYPE_ONE_MODULE1":
                        orgFilesDTO.setTreeType("1.工作重视");
                        break;
                    case "TYPE_ONE_MODULE2":
                        orgFilesDTO.setTreeType("2.政务环境");
                        break;
                    case "TYPE_ONE_MODULE3":
                        orgFilesDTO.setTreeType("3.法治环境");
                        break;
                    case "TYPE_ONE_MODULE4":
                        orgFilesDTO.setTreeType("4.营商环境");
                        break;
                    case "TYPE_ONE_MODULE5":
                        orgFilesDTO.setTreeType("5.人文环境");
                        break;
                    case "TYPE_ONE_MODULE6":
                        orgFilesDTO.setTreeType("6.服务环境");
                        break;
                    default:
                        break;
                }
            }
        }



        return list;
    }

    @Override
    public void upDataOrgStatus(int scoreType, int orgId) {
        switch (scoreType){
            case 1 :{
               int cont =  makeScoreMapper.contScore(1,orgId);
               if (cont == 210){
                    orgMapper.upDataLeaderStatus(1,orgId);
               }
               break;
            }
            case 2 :{
                int cont =  makeScoreMapper.contScore(2,orgId);
                if (cont == 7){
                    orgMapper.upDataExpertStatus(1,orgId);
                }
                break;
            }
            case 3:{
                int cont =  makeScoreMapper.contScore(3,orgId);
                OrgInfoDTO orgInfoDTO = orgMapper.findOrgByOrgId(orgId);
                if(cont == userMapper.allTypeCont(6)){
                    orgMapper.upDataAdmStatus(1,orgId);
                }
                else if (cont == userMapper.searchAccountByLoginNameListCont(orgInfoDTO.getOrgName()))
                {
                    orgMapper.upDataAdmStatus(1,orgId);
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public List<OrgInfoDTO> addAccountInfo(List<OrgInfoDTO> orgInfoDTOS) {
        for (OrgInfoDTO orgInfoDTO: orgInfoDTOS){
            orgInfoDTO.setUnitList(userMapper.allByType(4));

            orgInfoDTO.setExpertList(fixIsScore(userMapper.allByType(3),orgInfoDTO.getOrgId()));

        }



        return orgInfoDTOS;
    }

    @Override
    public List<LoginUserPO> fixIsScore(List<LoginUserPO> loginUserPOS, int orgId){
        for (LoginUserPO loginUserPO :loginUserPOS){
            if (makeScoreMapper.expertContScoreByAccountAndOrgId(loginUserPO.getId(),orgId)>0){
                loginUserPO.setIsScore(1);
            }
        }
        return loginUserPOS;
    }

}
