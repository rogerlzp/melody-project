package com.melody.service;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.melody.admin.api.SysRolePermService;
import com.melody.admin.dto.SysRolePerm;
import com.melody.dao.SysRolePermMapper;
//import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Service;


@Service(group = "sysRolePermService")
public class SysRolePermServiceImpl extends ServiceImpl<SysRolePermMapper, SysRolePerm> implements SysRolePermService {
}
