package com.acreath.gasycp.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: longteng
 * @date: 2018/12/8 21:12
 * @description:
 */
public class AdminListDTO {
    @JSONField(name = "org_id")
    private int orgId;
    @JSONField(name = "role")
    private String role;

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "adminListDTO{" +
                "orgId=" + orgId +
                ", role='" + role + '\'' +
                '}';
    }
}
