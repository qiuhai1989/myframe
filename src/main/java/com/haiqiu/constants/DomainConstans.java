package com.haiqiu.constants;


import java.util.ResourceBundle;

public class DomainConstans {

	/** 接口配置. */
	public static ResourceBundle	domain			= ResourceBundle.getBundle("config/domain");

	/** 当前项目配置的 css的域名. */
	public static final String		DOMAIN_CSS		= domain.getString("domain.css");

	/** 当前项目配置的 js的域名. */
	public static final String		DOMAIN_JS		= domain.getString("domain.js");

	/** 当前项目配置的 image 的域名. */
	public static final String		DOMAIN_IMAGE	= domain.getString("domain.image");

	/** 当前项目配置的媒体资源 */
	public static final String		DOMAIN_RESOURCE	= domain.getString("domain.resource");

	
}
