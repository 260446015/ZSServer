package com.huishu.ZSServer.entity.user;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author ydw
 * Created on 2018/5/22
 */
@Table(name = "t_user_role")
@Entity
public class Role implements Serializable{

    private static final long serialVersionUID = -6818460794496468926L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String rolename;

    @OneToMany(targetEntity = UserBase.class,mappedBy = "role")
    private Set<UserBase> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_permission",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    @JsonIgnore
    private Set<UserPermission> permissions;

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserBase> getUsers() {
        return users;
    }

    public void setUsers(Set<UserBase> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
