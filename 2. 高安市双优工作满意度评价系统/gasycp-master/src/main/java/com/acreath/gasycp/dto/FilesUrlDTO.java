package com.acreath.gasycp.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: longteng
 * @date: 2018/11/29 22:23
 * @description:
 */
public class FilesUrlDTO {
    @JSONField(name = "type_one_module1")
    private String TypeOneModule1;
    @JSONField(name = "type_one_module2")
    private String TypeOneModule2;
    @JSONField(name = "type_one_module3")
    private String TypeOneModule3;
    @JSONField(name = "type_one_module4")
    private String TypeOneModule4;
    @JSONField(name = "type_one_module5")
    private String TypeOneModule5;
    @JSONField(name = "type_one_module6")
    private String TypeOneModule6;

    public String getTypeOneModule1() {
        return TypeOneModule1;
    }

    public void setTypeOneModule1(String typeOneModule1) {
        TypeOneModule1 = typeOneModule1;
    }

    public String getTypeOneModule2() {
        return TypeOneModule2;
    }

    public void setTypeOneModule2(String typeOneModule2) {
        TypeOneModule2 = typeOneModule2;
    }

    public String getTypeOneModule3() {
        return TypeOneModule3;
    }

    public void setTypeOneModule3(String typeOneModule3) {
        TypeOneModule3 = typeOneModule3;
    }

    public String getTypeOneModule4() {
        return TypeOneModule4;
    }

    public void setTypeOneModule4(String typeOneModule4) {
        TypeOneModule4 = typeOneModule4;
    }

    public String getTypeOneModule5() {
        return TypeOneModule5;
    }

    public void setTypeOneModule5(String typeOneModule5) {
        TypeOneModule5 = typeOneModule5;
    }

    public String getTypeOneModule6() {
        return TypeOneModule6;
    }

    public void setTypeOneModule6(String typeOneModule6) {
        TypeOneModule6 = typeOneModule6;
    }

    @Override
    public String toString() {
        return "FilesUrlDTO{" +
                "TypeOneModule1='" + TypeOneModule1 + '\'' +
                ", TypeOneModule2='" + TypeOneModule2 + '\'' +
                ", TypeOneModule3='" + TypeOneModule3 + '\'' +
                ", TypeOneModule4='" + TypeOneModule4 + '\'' +
                ", TypeOneModule5='" + TypeOneModule5 + '\'' +
                ", TypeOneModule6='" + TypeOneModule6 + '\'' +
                '}';
    }
}
