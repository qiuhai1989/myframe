package com.haiqiu.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
        name = "Result"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class JSONResult<T> implements Serializable {
    private static final long serialVersionUID = -885562847752896396L;
    private boolean succeed = true;
    private int code = 0;
    private String msg = "";
    private T data;

    public JSONResult() {
    }

    public JSONResult(String msg) {
        this.setMsg(msg);
    }

    public JSONResult(T data) {
        this.data = data;
    }

    public boolean isSucceed() {
        return this.succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.succeed = false;
        this.code = 500;
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
