package com.melody.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.melody.admin.dto.SysPerm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermMapper extends BaseMapper<SysPerm> {

    List<SysPerm> getPermsByUserId(@Param("userId") String userId);

    List<SysPerm> getPermsByRoleId(@Param("roleId") String roleId);

    void saveOrUpdate(@Param("perms") List<SysPerm> perms);



}
