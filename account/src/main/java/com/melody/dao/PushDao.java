package com.melody.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * Created by zhanggt on 2016-2-20.
 * 
 */
public interface PushDao {

	List<String> selectDeviceByUser(List<Long> userList);

	int insertUserDevice(@Param(value = "registration_id") String registrationId, @Param(value = "user_id") long userId);
	
	int updateUserDevice(@Param(value = "registration_id") String registrationId, @Param(value = "user_id") long userId);

	int insertPushLog(Map<String, Object> pushEnter);
	
	int updateUserISNull(@Param(value = "user_id") long userId);
}
