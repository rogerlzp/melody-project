package com.melody.redis;

import com.melody.common.constant.BusinessCodes;

import com.melody.common.utils.ThreadUtil;

import com.melody.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * Created by zhanggt on 2016-1-13.
 * 
 */
@Component
public class RedisLocker {

	@Autowired
	private RedisCache redisCache;

	private Log logger = LogFactory.getLog(this.getClass());

	private static final int LOCK_TIMEOUT_SECOND_DEFAULT = 60;

	private static final int GET_LOCK_COUNTS_DEFAULT = 150;

	private static final int GET_LOCK_INTERVAL_MILLISECOND_DEFAULT = 500;

	public void lock(String key) {
		lock(key, LOCK_TIMEOUT_SECOND_DEFAULT);
	}

	public void lock(String key, int timeout) {
		lock(key, timeout, GET_LOCK_COUNTS_DEFAULT, GET_LOCK_INTERVAL_MILLISECOND_DEFAULT);
	}

	public void lock(String key, int timeoutSecond, int getLockCounts, int getLockIntervalMillisecond) {
		try {
			long result = 0;
			for (int i = 0; i < getLockCounts; i++) {
				if (redisCache.incr(key) == 1) {
					redisCache.expire(key, timeoutSecond);
					return;
				}
				if (logger.isDebugEnabled()) {
					logger.debug("lock key" + key + "get lock timers : " + result);
				}
				ThreadUtil.sleep(getLockIntervalMillisecond);
			}
			logger.error("get lock timeout, key:" + key);
			throw new BusinessException(BusinessCodes.ERROR);
		} catch (Exception e) {
			logger.error("get lock failure, key:" + key, e);
			throw new BusinessException(BusinessCodes.ERROR);
		}
	}

	public void unlock(String lockKey) {
		try {
			redisCache.delKey(lockKey);
		} catch (Exception e) {
			logger.error("unlock failure, lockKey" + lockKey, e);
			throw new BusinessException(BusinessCodes.ERROR);
		}
	}
}
