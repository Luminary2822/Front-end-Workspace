package com.acreath.gasycp.dto;

import java.util.List;

/**
 * @author: longteng
 * @date: 2018/11/19 12:50
 * @description:test
 */
public class BootstrapTableDTO {
    private int total;

    private List<OrgFilesDTO> rows;

    @Override
    public String toString() {
        return "BootstrapTableDTO{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrgFilesDTO> getRows() {
        return rows;
    }

    public void setRows(List<OrgFilesDTO> rows) {
        this.rows = rows;
    }
}
