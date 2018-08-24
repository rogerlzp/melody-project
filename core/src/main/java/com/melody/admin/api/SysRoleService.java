package com.melody.admin.api;


import com.baomidou.mybatisplus.service.IService;
import com.melody.admin.dto.SysRole;
import com.melody.vo.AuthVo;

import java.util.List;
import java.util.Set;

public interface SysRoleService extends IService<SysRole> {

    Set<AuthVo> getRolesByUserId(String userId);

    List<String> getRoleIdsByUserId(String userId);

    boolean checkRidsContainRval(List<String> rids, String rval);

    boolean checkUidContainRval(String uid, String rval);

}
