package com.melody.admin.api;


import com.baomidou.mybatisplus.service.IService;
import com.melody.admin.dto.SysPerm;
import com.melody.vo.AuthVo;

import java.util.List;
import java.util.Set;

public interface SysPermService extends IService<SysPerm> {

    Set<AuthVo> getPermsByUserId(String userId);

    List<SysPerm> getPermsByRoleId(String roleId);

    void saveOrUpdate(List<SysPerm> perms);



}
