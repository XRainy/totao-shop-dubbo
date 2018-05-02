package com.taotao.dubbo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author : dx
 * @aate : 2017/11/4
 * Description :
 */
public class CatNode implements Serializable{
    private static final long serialVersionUID = 1L;

    private String n;
    private String u;
    private List<?> i;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public List<?> getI() {
        return i;
    }

    public void setI(List<?> i) {
        this.i = i;
    }
}
