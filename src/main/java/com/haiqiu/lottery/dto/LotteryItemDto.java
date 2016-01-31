package com.haiqiu.lottery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by T430S on 2016/1/31.
 */
public class LotteryItemDto implements Serializable {

    private static final long serialVersionUID = 5677530558579570656L;
    @JsonProperty(value="amount")
    private Float amount;
    @JsonProperty(value="wishing")
    private String wishing;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }
}
