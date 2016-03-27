package com.haiqiu.constants;


import com.haiqiu.utils.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class DomainUtil {
	/**
	 * 获得二级域名 ,要是Validator.isNull( **),返回 contextPath
	 * 
	 * @param request
	 * @param domainType
	 * @return
	 */
	public static String getDomain(HttpServletRequest request,DomainType domainType){
		ServletContext servletContext = request.getSession().getServletContext();
		return getDomain(servletContext, domainType);
	}

	/**
	 * 获得二级域名 ,要是Validator.isNull( **),返回 contextPath
	 * 
	 * @param servletContext
	 * @param domainType
	 * @return
	 */
	public static String getDomain(ServletContext servletContext,DomainType domainType){
		String domain = getDomain(domainType);
		if (StringUtils.isBlank(domain)){
			String contextPath = servletContext.getContextPath();
			domain = contextPath;
		}
		return domain;
	}

	/**
	 * 直接读取配置的domainType,用于调度等非 Web环境<br>
	 *
	 * @param domainType
	 * @return
	 */
	public static String getDomain(DomainType domainType){
		String domain = null;
		switch (domainType) {
			case CSS:// css
				domain = DomainConstans.DOMAIN_CSS;
				break;
			case JS:// js
				domain = DomainConstans.DOMAIN_JS;
				break;
			case IMAGE:// 图片
				domain = DomainConstans.DOMAIN_IMAGE;
				break;
			case RESOURCE:// PDP商品图片
				domain = DomainConstans.DOMAIN_RESOURCE;
				break;
			default:
				throw new IllegalArgumentException("domainType can't support!");
		}
		return domain;
	}
}
