package com.acreath.gasycp.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author: longteng
 * @date: 2018/11/18 19:07
 * @description:
 */
public class OrgFilesDTO {

    @JSONField(name = "id")
    private  int id;
    @JSONField(name = "org_id")
    private int orgId;
    @JSONField(name = "file_name")
   private String fileName;
    @JSONField(name = "file_url")
    private String fileUrl;
    @JSONField(name = "create_at" ,format = "yyyy-MM-dd" )
    private Date createAt;
    @JSONField(name = "one_type")
    private String oneType;
    @JSONField(name = "two_type")
    private String twoType;
    @JSONField(name = "tree_type")
    private String treeType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOneType() {
        return oneType;
    }

    public void setOneType(String oneType) {
        this.oneType = oneType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTwoType() {
        return twoType;
    }

    public void setTwoType(String twoType) {
        this.twoType = twoType;
    }

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "OrgFilesDTO{" +
                "orgId=" + orgId +
                ", fileUrl='" + fileUrl + '\'' +
                ", createAt=" + createAt +
                ", twoType='" + twoType + '\'' +
                ", treeType='" + treeType + '\'' +
                '}';
    }
}
