package com.melody.gateway.dto;


import com.melody.base.GeneralEnter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Created by zhanggt on 2016-2-18.
 * 
 */
public class PushEnter extends GeneralEnter implements Serializable {
	
	
	public static final String EXTRAS_URI = "url";

	/**
	 * 
	 */
	private static final long serialVersionUID = 5137749647310185906L;

	/**
	 * android ios 默认为all
	 */
	private List<String> platform = new ArrayList<String>();

	private List<String> tag = new ArrayList<String>();

	private List<String> tagAnd = new ArrayList<String>();

	private List<String> alias = new ArrayList<String>();

	private List<String> registrationId = new ArrayList<String>();

	private List<Long> userId = new ArrayList<Long>();
	
	private String flag;//wangdejun 是否全部发送标志，0或空为否(不全部发送)，1为是(全部发送)

	/**
	 * require
	 */
	private String alert;

	private String title;

	/**
	 * Android SDK 可设置通知栏样式，这里根据样式 ID 来指定该使用哪套样式。
	 */
	private int builderId = -999;

	/**
	 * 推送当前用户不在线时，为该用户保留多长时间的离线消息，以便其上线时再次推送。默认 86400 （1 天），最长 10 天。设置为 0
	 * 表示不保留离线消息，只有推送当前在线的用户可以收到。
	 */
	private int timeToLive = -1;

	private Map<String, String> extras = new HashMap<String, String>();

	public List<String> getPlatform() {
		return platform;
	}

	public void setPlatform(List<String> platform) {
		if (platform != null) {
			this.platform = platform;
		}
	}

	public List<String> getTag() {
		return tag;
	}

	public void setTag(List<String> tag) {
		if (tag != null) {
			this.tag = tag;
		}
	}

	public List<String> getTagAnd() {
		return tagAnd;
	}

	public void setTagAnd(List<String> tagAnd) {
		if (tagAnd != null) {
			this.tagAnd = tagAnd;
		}
	}

	public List<String> getAlias() {
		return alias;
	}

	public void setAlias(List<String> alias) {
		if (alias != null) {
			this.alias = alias;
		}
	}

	public List<String> getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(List<String> registrationId) {
		if (registrationId != null) {
			this.registrationId = registrationId;
		}
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBuilderId() {
		return builderId;
	}

	public void setBuilderId(int builderId) {
		this.builderId = builderId;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		if (extras != null) {
			this.extras = extras;
		}
	}

	public void setExtras(String key, String value) {
		this.extras.put(key, value);
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public List<Long> getUserId() {
		return userId;
	}

	public void setUserId(List<Long> userId) {
		this.userId = userId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
