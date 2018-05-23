package com.huishu.ManageServer.entity.dbFirst;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ydw
 * Created on 2018/5/22
 */
@Table(name = "t_role_permission")
@Entity
public class RolePermission implements Serializable{
    private static final long serialVersionUID = -4182954839830713781L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private UserPermission permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public UserPermission getPermission() {
        return permission;
    }

    public void setPermission(UserPermission permission) {
        this.permission = permission;
    }
}
