package com.melody.dao;

import com.melody.product.dto.SPU;
import com.melody.system.dto.SysCustomerLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper {


    List<SysCustomerLevel> getCustomerLevel();
}
