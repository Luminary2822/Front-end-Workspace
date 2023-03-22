package com.acreath.gasycp.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: longteng
 * @date: 2018/11/30 00:09
 * @description:
 */
public class MassQuestionDTO {
    @JSONField(name = "phone")
    private String phone;
    @JSONField(name = "org_id")
    private int orgId;
    @JSONField(name = "org_name")
    private String orgName;
    @JSONField(name = "is_good")
    private int isGood;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getIsGood() {
        return isGood;
    }

    public void setIsGood(int isGood) {
        this.isGood = isGood;
    }

    @Override
    public String toString() {
        return "MassQuestionDTO{" +
                "phone='" + phone + '\'' +
                ", orgId=" + orgId +
                ", orgName=" + orgName +
                ", isGood=" + isGood +
                '}';
    }
}
