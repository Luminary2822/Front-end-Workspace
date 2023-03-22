package com.acreath.gasycp.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author: longteng
 * @date: 2018/10/23 13:00
 * @description:
 */
public class LoginUserDTO implements Serializable {
    private static final long serialVersionUID = -6148782076805864643L;

    @JSONField(name = "session_key")
    private String sessionKey;

    @JSONField(name = "login_name")
    private String loginName;

    @JSONField(name = "identitys")
    private String identitys;

    @JSONField(name = "org_id")
    private int orgId;

    @JSONField(name = "detail_info")
    private String detailInfo;


    public String getIdentitys() {
        return identitys;
    }

    public void setIdentitys(String identitys) {
        this.identitys = identitys;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "sessionKey='" + sessionKey + '\'' +
                ", loginName='" + loginName + '\'' +
                ", identitys='" + identitys + '\'' +
                '}';
    }
}
