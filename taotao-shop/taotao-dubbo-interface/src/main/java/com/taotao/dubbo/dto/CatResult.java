package com.taotao.dubbo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author : dx
 * @aate : 2017/11/4
 * Description :
 */
public class CatResult implements Serializable{
    private static final long serialVersionUID = 1L;

    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
