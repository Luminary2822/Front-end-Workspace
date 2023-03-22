package com.acreath.gasycp.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: longteng
 * @date: 2018/12/7 21:44
 * @description:
 */
public class OrgQuestionDTO {
    @JSONField(name = "account_id")
    private int accountId;
    @JSONField(name = "option_id")
   private String optionId;
    @JSONField( name = "option_number")
    private  int optionNumber;

    public int getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    @Override
    public String toString() {
        return "OrgQuestionDTO{" +
                "accountId=" + accountId +
                ", optionId='" + optionId + '\'' +
                '}';
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }


}
