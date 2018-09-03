package com.melody.dao;

import com.melody.system.dto.DictItem;

import java.util.List;

/**
 * Created by liuyw on 2016/1/19.
 */
public interface SysConfigDao {

    List<DictItem> refreshSysParameter();

}
