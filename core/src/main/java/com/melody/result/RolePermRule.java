package com.melody.result;



import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * url和角色对应实体
 * @author konghang
 */
public class RolePermRule {

    /**
     * 资源URL
     */

    private String url;
    /**
     * 访问资源所需要的角色列表，多个列表用逗号间隔
     */

    private String needRoles;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNeedRoles() {
        return this.needRoles;
    }

    public void setNeedRoles(String needRoles) {
        this.needRoles = needRoles;
    }

    /**
     * 将url needRoles 转化成shiro可识别的过滤器链：url=jwt[角色1、角色2、角色n]
     * @return
     */
    public StringBuilder toFilterChain() {

        if (StringUtils.isEmpty(url)){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> setRole = new HashSet<>();
        setRole.addAll(Arrays.asList(this.getNeedRoles().split(",")));

        // 约定若role_anon角色拥有此uri资源的权限,则此uri资源直接访问不需要认证和权限
        if (!StringUtils.isEmpty(this.getNeedRoles()) && setRole.contains("role_anon")) {
            stringBuilder.append("anon");
        }
        //  其他自定义资源uri需通过jwt认证和角色认证
        if (!StringUtils.isEmpty(this.getNeedRoles()) && !setRole.contains("role_anon")) {
            stringBuilder.append("jwt"+"["+this.getNeedRoles()+"]");
        }

        return stringBuilder.length() > 0 ? stringBuilder : null;
    }

    @Override
    public String toString() {
        return "RolePermRule [url="+url+", needRoles="+needRoles+"]";
    }
}
