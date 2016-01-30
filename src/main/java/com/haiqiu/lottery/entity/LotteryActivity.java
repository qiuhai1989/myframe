package com.haiqiu.lottery.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.haiqiu.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by T430S on 2016/1/30.
 */
public class LotteryActivity extends BaseEntity {
    private static final long serialVersionUID = -1183838541106787577L;
    @JsonProperty(value="hb_type",required=true)
    private String hbType ;
    @JsonProperty(value="amt_type",required=true)
    private String amType;
    @JsonProperty(value="total_amount",required=true)
    private Float totalAmount;
    @JsonProperty(value="total_num")
    private Integer totalNum;

    private String wishing;

    private String sign;


    public String getHbType() {
        return hbType;
    }

    public void setHbType(String hbType) {
        this.hbType = hbType;
    }

    public String getAmType() {
        return amType;
    }

    public void setAmType(String amType) {
        this.amType = amType;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
