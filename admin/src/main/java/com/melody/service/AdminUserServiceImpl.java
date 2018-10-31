package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.api.AdminUserService;
import com.melody.common.constant.UserReferralEnum;
import com.melody.common.constant.UserStatusEnum;
import com.melody.dao.AdminSKUMapper;
import com.melody.dao.AdminUserMapper;
import com.melody.product.dto.*;
import com.melody.system.dto.SysCustomerLevel;
import com.melody.user.dto.Role;
import com.melody.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(group = "adminUserService", timeout = 10000)
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    AdminUserMapper adminUserMapper;

    @Autowired
    BaseServiceImpl baseService;

    @Override
    public List<SysCustomerLevel> getSysCustomerLevel() {

        List<SysCustomerLevel> sysCustomerLevelList = adminUserMapper.getCustomerLevel();
        return sysCustomerLevelList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Long addUser(User user, Role role) {
        Long userId = baseService.getNextSequence("TS_USER");
        user.setUserId(userId);
        user.setReferralCode(UserReferralEnum.COMPANY_REFERRAL.getEnName());
        user.setStatus(UserStatusEnum.USER_STATUS_COMPANY_CRETAED.getEnName()); // 公司新建，有效

        int insertResult = adminUserMapper.insertUser(user);

        // user role

        // 添加属性成功后，在添加特性 TODO: 抛出异常
        if (insertResult == 1) {
            if ( role != null) {
                Long userRoleId = baseService.getNextSequence("TR_USER_ROLE");
                adminUserMapper.insertUserRole(userRoleId, userId, role.getId());
            }

            return userId;
        }
        return -1L;
    }

    @Override
    public int addRole(Role role) {
        int roleId = baseService.getNextSequence("TT_ROLE").intValue();
        role.setId(roleId);
       int insertResult = adminUserMapper.insertRole(role);
        if (insertResult == 1) {
            return roleId;
        }
        return -1;
    }

    @Override
    public int deleteUserByUserId(Long userId) {
        int result = adminUserMapper.updateUserStatus(userId, UserStatusEnum.USER_STATUS_DELETED.getEnName());
        return result;
    }


    @Override
    public Page<User> queryUserList(Page page) {
        int currentPage = page.getCurrent() - 1; // current为1， 所以往后减一位
        int pageSize = page.getSize();
        int totalCount = adminUserMapper.countAllUser();// 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        String validStatus = "1";
        List<User> records = adminUserMapper.queryUserList(pager.getOffset(), pageSize, validStatus);

        Page<User> brandPage = new Page<User>();
        brandPage.setRecords(records);
        brandPage.setSize(pageSize);
        brandPage.setCurrent(currentPage);
        brandPage.setTotal(totalCount);
        return brandPage;
    }


    @Override
    public Page<Role> queryRoleList(Page page) {
        int currentPage = page.getCurrent() - 1; // current为1， 所以往后减一位
        int pageSize = page.getSize();
        int totalCount = adminUserMapper.countAllRole();// 获取总条数
        Pager pager = new Pager(pageSize, totalCount, currentPage);
        String validStatus = "1";
        List<Role> records = adminUserMapper.queryRoleList(pager.getOffset(), pageSize);

        Page<Role> brandPage = new Page<>();
        brandPage.setRecords(records);
        brandPage.setSize(pageSize);
        brandPage.setCurrent(currentPage);
        brandPage.setTotal(totalCount);
        return brandPage;
    }
}
