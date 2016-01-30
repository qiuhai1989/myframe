package com.haiqiu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by T430S on 2016/1/30.
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5129450115498848708L;
    private Long id;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
