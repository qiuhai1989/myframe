/*
 * @(#)ServiceExceptionEntity.java 1.0 2014-5-22 下午4:57:07
 *
 *Copyright EWAYTEC. All Rights Reserved.
 */
package com.haiqiu.exception;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author yong.he
 *
 */
@XmlRootElement(name = "exception")
public class ServiceExceptionEntity implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	/* 错误码，默认为0 */
	private int code = 0;

	private String msg;
	
	public ServiceExceptionEntity() {
		
	}

	public ServiceExceptionEntity(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
