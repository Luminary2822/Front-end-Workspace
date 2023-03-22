package com.acreath.gasycp.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: longteng
 * @date: 2018/11/27 19:13
 * @description:
 */
public class MakeScoreDTO {
    @JSONField(name = "score")
    private int score;
    @JSONField(name = "org_id")
    private int orgId;
    @JSONField(name = "account_id")
    private int accountId;
    @JSONField(name = "score_type")
    private int scoreType;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getScoreType() {
        return scoreType;
    }

    public void setScoreType(int scoreType) {
        this.scoreType = scoreType;
    }

    @Override
    public String toString() {
        return "MakeScoreDTO{" +
                "score=" + score +
                ", orgId=" + orgId +
                ", accountId=" + accountId +
                ", scoreType=" + scoreType +
                '}';
    }
}
