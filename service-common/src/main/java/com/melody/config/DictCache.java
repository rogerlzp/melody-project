package com.melody.config;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liuyw on 2016/1/19.
 */
public class DictCache implements Serializable {
    public static String CONCAT_FLAG = "|";
    public static String CONCAT_CRM = "CRM";
    private Map<String, String> sysParamCache = new ConcurrentHashMap<String, String>();

    private static DictCache instance = null;

    private DictCache() {
    }

    public synchronized static DictCache getInstance() {
        if (instance == null){
            instance = new DictCache();
        }
        return instance;
    }

    public void init(Map<String, String> sysParamCache) {
        this.sysParamCache=sysParamCache;
    }

    public String getSysParameter(String className, String itemName) {
        String key = className + CONCAT_FLAG + itemName;
        String value = sysParamCache.get(key);
        return value;
    }

    public String getCRMSysParameter(String itemName) {
        String key =  CONCAT_CRM+ CONCAT_FLAG + itemName;
        String value = sysParamCache.get(key);
        return value;
    }
}
