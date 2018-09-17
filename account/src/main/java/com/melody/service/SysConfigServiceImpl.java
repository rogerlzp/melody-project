package com.melody.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.melody.common.constant.RedisCodes;
import com.melody.config.DictCache;
import com.melody.dao.SysConfigDao;
import com.melody.redis.RedisCache;
import com.melody.redis.RedisLocker;
import com.melody.system.api.SysConfigService;

import com.melody.system.dto.DictItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liuyw on 2016/1/19.
 */

@Service(group="sysConfigService", timeout = 10000)
public class SysConfigServiceImpl implements SysConfigService {
    public static String CONCAT_FLAG = "|";
    public static String CONCAT_DEFAULT = "ITEM";//default
    @Autowired
    RedisCache redisCache;
    @Autowired
    SysConfigDao sysConfigDao;
    @Autowired
    RedisLocker redisLocker;

    @Override
    public void refreshSysParameter(String sysType) {
        try {
            redisLocker.lock(sysType);
            Map<String, String> sysParamCache = new ConcurrentHashMap<String, String>();
            List<DictItem> list = sysConfigDao.refreshSysParameter();
            if (list != null) {
                for (DictItem dictItem : list) {
                    sysParamCache.put(dictItem.getClassName() + DictCache.CONCAT_FLAG + dictItem.getItemName(), dictItem.getItemValue());
                }
            }
            redisCache.delKey(RedisCodes.DICTCACHE);
            redisCache.hashMultipleSet(RedisCodes.DICTCACHE, sysParamCache);
            DictCache.getInstance().init(sysParamCache);
        } finally {
            redisLocker.unlock(sysType);
        }
    }

    @Override
    public String getSysParameter(String s, String s1) {
        return null;
    }

    @Override
    public String getSysParameter(String item) {
        String key = CONCAT_DEFAULT + CONCAT_FLAG + item;
        String itemValue = redisCache.hashGet(RedisCodes.DICTCACHE, key);
        if (StringUtils.isEmpty(itemValue)) {
            this.refreshSysParameter(SysConfigService.ID);
            itemValue = redisCache.hashGet(RedisCodes.DICTCACHE, key);
        }
        return itemValue;
    }
}
