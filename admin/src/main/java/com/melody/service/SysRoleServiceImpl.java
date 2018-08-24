package com.melody.service;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.melody.admin.api.SysRoleService;
import com.melody.admin.dto.SysRole;
import com.melody.dao.SysRoleMapper;
import com.melody.vo.AuthVo;
import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service(group = "sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public Set<AuthVo> getRolesByUserId(String userId) {
        List<SysRole> list = baseMapper.getRolesByUserId(userId);
        return list.stream().map(r->new AuthVo(r.getRname(),r.getRval())).collect(Collectors.toSet());
    }

    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        return baseMapper.getRoleIdsByUserId(userId);
    }

    @Override
    public boolean checkRidsContainRval(List<String> rids, String rval) {
        if (rids.isEmpty()) return false;
        Boolean re = baseMapper.checkRidsContainRval(rids, rval);
        return re==null?false:re.booleanValue();
    }

    @Override
    public boolean checkUidContainRval(String uid, String rval) {
        if (StringUtils.isBlank(uid)) return false;
        Boolean re = baseMapper.checkUidContainRval(uid, rval);
        return re==null?false:re.booleanValue();
    }
}
