package com.haiqiu.lottery.entity;

import com.haiqiu.entity.BaseEntity;
import com.haiqiu.entity.Book;

import java.io.Serializable;

/**
 * Created by T430S on 2016/1/30.
 */
public class LotteryItem extends BaseEntity {
    private static final long serialVersionUID = 7066383757435415414L;

    private Long lotteryId;

    private Float amount;

    private String wishing;

    private Boolean isOpen;

    public Long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
    }

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

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }
}
