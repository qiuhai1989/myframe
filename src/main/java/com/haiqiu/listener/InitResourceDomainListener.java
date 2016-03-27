package com.haiqiu.listener;


import com.haiqiu.constants.DomainType;
import com.haiqiu.constants.DomainUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class InitResourceDomainListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletContext servletContext = sre.getServletContext();
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

		String domainCss = DomainUtil.getDomain(servletContext, DomainType.CSS);
		String domainJs = DomainUtil.getDomain(servletContext,DomainType.JS);
		String domainImage = DomainUtil.getDomain(servletContext,DomainType.IMAGE);
		String domainResource = DomainUtil.getDomain(servletContext,DomainType.RESOURCE);
		
		request.setAttribute("domain_base", request.getContextPath());
		request.setAttribute("domain_css", domainCss);
		request.setAttribute("domain_js", domainJs);
		request.setAttribute("domain_image", domainImage);
		request.setAttribute("domain_resource", domainResource);
		
	}

}
