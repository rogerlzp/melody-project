package com.melody.admin.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * created by CaiBaoHong at 2018/4/17 14:55<br>
 */
@TableName("SYS_ROLE_PERM")
public class SysRolePerm implements Serializable {

    @TableField("ROLE_ID")
    private String roleId;
    @TableField("PERM_VAL")
    private String permVal;
    @TableField("PERM_TYPE")
    private Integer permType;

    public SysRolePerm() {
    }

    public SysRolePerm(String roleId, String permVal,Integer permType) {
        this.roleId = roleId;
        this.permVal = permVal;
        this.permType = permType;
    }

    public Integer getPermType() {
        return permType;
    }

    public void setPermType(Integer permType) {
        this.permType = permType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermVal() {
        return permVal;
    }

    public void setPermVal(String permVal) {
        this.permVal = permVal;
    }
}
