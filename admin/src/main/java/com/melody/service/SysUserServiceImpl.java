package com.melody.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.melody.admin.api.SysUserService;
import com.melody.admin.dto.SysUser;
import com.melody.dao.SysUserMapper;
//import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Service;


@Service(group = "sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public Page<SysUser> queryUserIncludeRoles(Page page, String nick) {
        return page.setRecords(baseMapper.selectUserIncludeRoles(page, nick));
    }

}
