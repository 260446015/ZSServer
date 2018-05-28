package com.huishu.ManageServer.entity.dto;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author ydw
 * Created on 2018/5/28
 */
public class RolePermissionDto implements Serializable {
    private static final long serialVersionUID = -3312548448311307632L;

    private Long roleId;

    private Long permissionId;

    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
