package com.melody.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.melody.admin.dto.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRolesByUserId(@Param("userId") String userId);

    List<String> getRoleIdsByUserId(@Param("userId") String userId);

    Boolean checkRidsContainRval(@Param("rids") List<String> rids, @Param("rval") String rval);

    Boolean checkUidContainRval(@Param("uid") String uid, @Param("rval") String rval);

}
