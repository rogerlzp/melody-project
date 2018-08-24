package com.melody.service;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.melody.admin.api.SysUserRoleService;
import com.melody.admin.dto.SysUserRole;
import com.melody.dao.SysUserRoleMapper;
//import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Service;

@Service(group = "sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
