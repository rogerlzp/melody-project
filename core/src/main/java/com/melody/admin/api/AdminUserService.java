package com.melody.admin.api;


import com.baomidou.mybatisplus.plugins.Page;
import com.melody.product.dto.Brand;
import com.melody.system.dto.SysCustomerLevel;
import com.melody.user.dto.UserLevel;

import java.util.List;

/**
 * 管理用户，设置会员体系，会员折扣
 */
public interface AdminUserService {

    // 获取会员分类
    List<SysCustomerLevel>  getSysCustomerLevel();

}

