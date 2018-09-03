package com.melody.web.config.shiro.filter;


import com.melody.result.RolePermRule;
import com.melody.web.config.shiro.rest.RestPathMatchingFilterChainResolver;
import com.melody.web.support.SpringContextHolder;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.*;

/**
 * 过滤器以及过滤url管理
 * @author konghang
 */
@Component
public class ShiroFilterChainManager {

    /**
     * 初始化获取过滤链
     */
    public Map<String, Filter> initFilters() {
        Map<String, Filter> filters = new LinkedHashMap<>();
        JwtStatelessFilter jwtFilter = new JwtStatelessFilter();
        filters.put("jwt",jwtFilter);
        return filters;
    }

    /**
     * 初始化获取过滤链规则
     * @return
     */
    public Map<String,String> initFilterChain() {
        Map<String,String> filterChain = new LinkedHashMap<>();
        // -------------anon 默认过滤器忽略的URL
        List<String> defaultAnon = Arrays.asList("/css/**","/js/**","/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/api-docs");
        defaultAnon.forEach(ignored -> filterChain.put(ignored,"anon"));
        // -------------dynamic 动态URL
        List<RolePermRule> rolePermRules = new ArrayList<>();
        // TODO: 2018/8/9  rolePermRules动态去数据库加载
        wrap(rolePermRules);
        if (null != rolePermRules) {
            rolePermRules.forEach(rule -> {
                StringBuilder Chain = rule.toFilterChain();
                if (null != Chain) {
                    filterChain.putIfAbsent(rule.getUrl(),Chain.toString());
                }
            });
        }
        List<String> user = Arrays.asList("/api/article/chosen","/api/article--GET","/api/article/**--GET","/**");
        user.forEach(ignored -> filterChain.put(ignored,"anon"));
        // -------------jwt jwt相关,无角色需求
        List<String> defaultJwt = Arrays.asList("/api/**");
        defaultJwt.forEach(jwt -> filterChain.put(jwt, "jwt"));
        return filterChain;
    }

    private void wrap(List<RolePermRule> rolePermRules){
        RolePermRule rolePermRule = new RolePermRule();
        rolePermRule.setUrl("/api/user/login");
        rolePermRule.setNeedRoles("role_anon");
        rolePermRules.add(rolePermRule);
        RolePermRule rule = new RolePermRule();
        rule.setUrl("/index--POST");
        rule.setNeedRoles("role_anon");
        rolePermRules.add(rule);
        RolePermRule rolePermRule1 = new RolePermRule();
        rolePermRule1.setUrl("/api/user/register");
        rolePermRule1.setNeedRoles("role_anon");
        rolePermRules.add(rolePermRule1);
    }

    /**
     * 动态重新加载过滤链规则
     */
    public void reloadFilterChain() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        AbstractShiroFilter abstractShiroFilter = null;
        try {
            abstractShiroFilter = (AbstractShiroFilter)shiroFilterFactoryBean.getObject();
            RestPathMatchingFilterChainResolver filterChainResolver = (RestPathMatchingFilterChainResolver)abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager)filterChainResolver.getFilterChainManager();
            filterChainManager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(this.initFilterChain());
            shiroFilterFactoryBean.getFilterChainDefinitionMap().forEach((k,v) -> filterChainManager.createChain(k,v));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
