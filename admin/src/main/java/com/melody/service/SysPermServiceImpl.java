package com.melody.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.melody.admin.api.SysPermService;
import com.melody.admin.dto.SysPerm;
import com.melody.dao.SysPermMapper;
import com.melody.vo.AuthVo;
//import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(group = "sysPermService")
public class SysPermServiceImpl extends ServiceImpl<SysPermMapper, SysPerm> implements SysPermService {

    @Override
    public Set<AuthVo> getPermsByUserId(String userId) {
        List<SysPerm> list = baseMapper.getPermsByUserId(userId);
        return list.stream().map(p->new AuthVo(p.getPname(),p.getPval())).collect(Collectors.toSet());
    }

    @Override
    public List<SysPerm> getPermsByRoleId(String roleId) {
        return baseMapper.getPermsByRoleId(roleId);
    }

    @Override
    public void saveOrUpdate(List<SysPerm> perms) {
        if (perms!=null&&!perms.isEmpty()){
            baseMapper.saveOrUpdate(perms);
        }
    }




}
