package com.acreath.gasycp.dto;

import com.acreath.gasycp.po.LoginUserPO;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/13 13:09
 * @description:
 */
public class OrgInfoDTO {
    @JSONField(name = "org_id")
    private int orgId;
    @JSONField(name = "org_name")
    private String orgName;
    @JSONField(name = "org_create_at" ,format = "yyyy-MM-dd")
    private Date orgCreateAt;
    @JSONField(name = "org_info")
    private String orgInfo;
    @JSONField(name = "org_person")
    private String orgPerson;
    @JSONField(name = "org_score")
    private int orgScore;
    @JSONField(name = "org_type")
    private int orgType;
    @JSONField(name = "file_status")
    private int fileStatus;
    @JSONField(name = "expert_status")
    private int expertStatus;
    @JSONField(name = "leader_status")
    private int leaderStatus;
    @JSONField(name = "adm_status")
    private int admStatus;
    @JSONField(name = "module1_status")
    private int module1Status;
    @JSONField(name = "module2_status")
    private int module2Status;
    @JSONField(name = "module3_status")
    private int module3Status;
    @JSONField(name = "module4_status")
    private int module4Status;
    @JSONField(name = "module5_status")
    private int module5Status;
    @JSONField(name = "module6_status")
    private int module6Status;

    @JSONField(name = "leader_list")
    private List<LoginUserPO> leaderList;

    @JSONField(name = "expert_list")
    private List<LoginUserPO> expertList;
    @JSONField(name = "unit_list")
    private List<LoginUserPO> unitList;

    public List<LoginUserPO> getExpertList() {
        return expertList;
    }

    public void setExpertList(List<LoginUserPO> expertList) {
        this.expertList = expertList;
    }

    public List<LoginUserPO> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<LoginUserPO> unitList) {
        this.unitList = unitList;
    }

    public List<LoginUserPO> getLeaderList() {
        return leaderList;
    }

    public void setLeaderList(List<LoginUserPO> leaderList) {
        this.leaderList = leaderList;
    }

    public int getModule1Status() {
        return module1Status;
    }

    public void setModule1Status(int module1Status) {
        this.module1Status = module1Status;
    }

    public int getModule2Status() {
        return module2Status;
    }

    public void setModule2Status(int module2Status) {
        this.module2Status = module2Status;
    }

    public int getModule3Status() {
        return module3Status;
    }

    public void setModule3Status(int module3Status) {
        this.module3Status = module3Status;
    }

    public int getModule4Status() {
        return module4Status;
    }

    public void setModule4Status(int module4Status) {
        this.module4Status = module4Status;
    }

    public int getModule5Status() {
        return module5Status;
    }

    public void setModule5Status(int module5Status) {
        this.module5Status = module5Status;
    }

    public int getModule6Status() {
        return module6Status;
    }

    public void setModule6Status(int module6Status) {
        this.module6Status = module6Status;
    }

    public int getAdmStatus() {
        return admStatus;
    }

    public void setAdmStatus(int admStatus) {
        this.admStatus = admStatus;
    }

    public int getLeaderStatus() {
        return leaderStatus;
    }

    public void setLeaderStatus(int leaderStatus) {
        this.leaderStatus = leaderStatus;
    }

    public int getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }


    public int getExpertStatus() {
        return expertStatus;
    }

    public void setExpertStatus(int expertStatus) {
        this.expertStatus = expertStatus;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getOrgCreateAt() {
        return orgCreateAt;
    }

    public void setOrgCreateAt(Date orgCreateAt) {
        this.orgCreateAt = orgCreateAt;
    }

    public String getOrgInfo() {
        return orgInfo;
    }

    public void setOrgInfo(String orgInfo) {
        this.orgInfo = orgInfo;
    }

    public String getOrgPerson() {
        return orgPerson;
    }

    public void setOrgPerson(String orgPerson) {
        this.orgPerson = orgPerson;
    }

    public int getOrgScore() {
        return orgScore;
    }

    public void setOrgScore(int orgScore) {
        this.orgScore = orgScore;
    }

    @Override
    public String toString() {
        return "OrgInfoDTO{" +
                "orgId=" + orgId +
                ", orgNmae='" + orgName + '\'' +
                ", orgCreateAt=" + orgCreateAt +
                ", orgInfo='" + orgInfo + '\'' +
                ", orgPerson='" + orgPerson + '\'' +
                ", orgScore=" + orgScore +
                '}';
    }
}
