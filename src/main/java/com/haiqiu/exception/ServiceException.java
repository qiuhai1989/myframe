package com.haiqiu.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.Serializable;

public class ServiceException extends WebApplicationException implements Serializable{

	private static final long serialVersionUID = 2993910753579035668L;

	private int code;

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ServiceException(int code) {
		this.code = code;
	}
	
	public ServiceException(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ServiceException(String msg) {
		this.msg = msg;
	}
	
	public ServiceException(String msg, Response response){
		super(response);
		this.msg = msg;
	}
	
	public ServiceException(int code, String msg, Response response){
		super(response);
		this.code = code;
		this.msg = msg;
	}
	
	public ServiceException(Response response){
		super(response);
	}
	
}
