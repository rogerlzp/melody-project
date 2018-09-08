package com.melody.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by liuyw on 2015/11/25.
 */
public interface SequenceDao {

    int getNextSequence(@Param(value = "tableName") String tableName);

    int updateSequence(@Param(value = "tableName") String tableName);
}

