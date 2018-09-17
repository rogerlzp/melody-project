package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.api.AdminUserService;
import com.melody.dao.AdminSKUMapper;
import com.melody.dao.AdminUserMapper;
import com.melody.product.dto.SPU;
import com.melody.system.dto.SysCustomerLevel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(group = "adminUserService", timeout = 10000)
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    AdminUserMapper adminUserMapper;

    @Override
    public List<SysCustomerLevel> getSysCustomerLevel() {

        List<SysCustomerLevel> sysCustomerLevelList = adminUserMapper.getCustomerLevel();
        return sysCustomerLevelList;
    }

}
