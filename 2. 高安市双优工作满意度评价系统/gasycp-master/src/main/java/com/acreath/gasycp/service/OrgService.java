package com.acreath.gasycp.service;

import com.acreath.gasycp.dto.OrgFilesDTO;
import com.acreath.gasycp.dto.OrgInfoDTO;
import com.acreath.gasycp.po.LoginUserPO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/13 13:28
 * @description:
 */
public interface OrgService {
    OrgInfoDTO getOrgInfo(int orgId);
    List<OrgFilesDTO> getFileType(List<OrgFilesDTO> list);
    void upDataOrgStatus(int scoreType,int orgId);
    List<OrgInfoDTO> addAccountInfo(List<OrgInfoDTO> orgInfoDTOS);
    List<LoginUserPO> fixIsScore(List<LoginUserPO> loginUserPOS, int orgId);
}

