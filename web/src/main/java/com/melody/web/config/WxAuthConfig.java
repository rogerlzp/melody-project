package com.melody.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


//@ConfigurationProperties(prefix = "wxapp")
/**
 * 
 * @author xiefengchang
 *
 */
@Configuration
@ConfigurationProperties(prefix = "wxapp")
public class WxAuthConfig {
	private String appId;
	
	private String secret;
	
	private String grantType;
	
	private String sessionHost;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getSessionHost() {
		return sessionHost;
	}

	public void setSessionHost(String sessionHost) {
		this.sessionHost = sessionHost;
	}
	
	

}
