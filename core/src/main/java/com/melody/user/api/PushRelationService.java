package com.melody.user.api;

import java.util.List;
import java.util.Map;

/**
 * 
 * Created by zhanggt on 2016-2-20.
 * 
 */
public interface PushRelationService {

	List<String> getDevice(List<Long> userList);

	void saveUserDevice(String registrationId, long userId);

	void savePushLog(Map<String, Object> pushEnter);
}
