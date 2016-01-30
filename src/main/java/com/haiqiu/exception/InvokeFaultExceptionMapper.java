package com.haiqiu.exception;

//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Locale;

@SuppressWarnings("rawtypes")
@Provider
public class InvokeFaultExceptionMapper implements ExceptionMapper {

	/*@Override
	public Response toResponse(ServiceException exception) {
		ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);  
        rb.type("application/json;charset=UTF-8");  
        rb.entity(new ServiceExceptionEntity(exception.getCode(), exception.getMessage()));  
        rb.language(Locale.SIMPLIFIED_CHINESE);  
        Response r = rb.build();  
        return r;
	}*/

	public Response toResponse(Throwable ex) {
		/*StackTraceElement[] trace = new StackTraceElement[1];
		trace[0] = ex.getStackTrace()[0];
		ex.setStackTrace(trace);*/
		ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		rb.type("application/json;charset=UTF-8");
		if (ex instanceof ServiceException) { // 自定义的异常类
			ServiceException e = (ServiceException) ex;
			ServiceExceptionEntity entity = new ServiceExceptionEntity(e.getCode(), e.getMsg());
			rb.entity(entity);
		} else {
			ServiceExceptionEntity entity = new ServiceExceptionEntity(500, "系统异常");
			rb.entity(entity);
		}
		rb.language(Locale.SIMPLIFIED_CHINESE);
		Response r = rb.build();
		return r;
	}
	
	
}



