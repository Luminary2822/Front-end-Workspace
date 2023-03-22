package com.acreath.gasycp.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: longteng
 * @date: 2018/11/19 11:55
 * @description:
 */
public class OrgFileTableDTO {
    @JSONField(name = "org_id")
    private int orgId;
    @JSONField(name = "one_type")
    private String oneType;

    @Override
    public String toString() {
        return "OrgFileTableDTP{" +
                "orgId=" + orgId +
                ", oneType='" + oneType + '\'' +
                '}';
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOneType() {
        return oneType;
    }

    public void setOneType(String oneType) {
        this.oneType = oneType;
    }
}
