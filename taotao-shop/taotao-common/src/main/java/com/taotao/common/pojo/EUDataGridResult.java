package com.taotao.common.pojo;

import java.util.List;

/**
 * @author : dx
 * @aate : 2017/10/22
 * Description :
 */
public class EUDataGridResult {
    private long total;
    private List<?> rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
