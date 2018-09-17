package com.melody.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.melody.common.constant.BusinessCodes;
import com.melody.dao.PushDao;
import com.melody.exception.BusinessException;
import com.melody.user.api.PushRelationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 
 * Created by zhanggt on 2016-2-20.
 * 
 */
@Service(group="pushRelationService", timeout = 10000)
public class PushRelationServiceImpl implements PushRelationService {

	@Autowired
	private PushDao pushDao;

	@Override
	public List<String> getDevice(List<Long> userList) {
		if (userList == null || userList.isEmpty()) {
			throw new BusinessException(BusinessCodes.GATEWAY_PUSH_USER_ID_IS_NULL);
		}
		List<String> devices = pushDao.selectDeviceByUser(userList);
		return devices;
	}

	@Override
	public void saveUserDevice(String registrationId, long userId) {
		if (userId > 0) {
			pushDao.updateUserISNull(userId);
		}
		int updateSize = pushDao.updateUserDevice(registrationId, userId);
		if (updateSize == 0) {
			pushDao.insertUserDevice(registrationId, userId);
		}
	}

	@Override
	public void savePushLog(Map<String, Object> pushEnter) {
	}

}
