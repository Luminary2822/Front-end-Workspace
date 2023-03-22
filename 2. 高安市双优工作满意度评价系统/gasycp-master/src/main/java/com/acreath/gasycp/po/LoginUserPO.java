package com.acreath.gasycp.po;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: longteng
 * @date: 2018/10/23 13:22
 * @description:
 */
public class LoginUserPO {
    @JSONField(name = "id")
    private int id;

    @JSONField(name = "session_key")
    private String sessionKey;

    @JSONField(name = "login_name")
    private String loginName;

    @JSONField(name = "identitys")
    private int identitys;

    @JSONField(name = "username")
    private String username;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "org_id")
    private int orgId;

    @JSONField(name = "is_score")
    private int isScore;
    @JSONField(name = "detail_info")
    private String detailInfo;

    public int getIdentitys() {
        return identitys;
    }

    public void setIdentitys(int identitys) {
        this.identitys = identitys;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public int getIsScore() {
        return isScore;
    }

    public void setIsScore(int isScore) {
        this.isScore = isScore;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginUserPO{" +
                "sessionKey='" + sessionKey + '\'' +
                ", loginName='" + loginName + '\'' +
                ", identitys='" + identitys + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
