
package com.huishu.ManageServer.repository.first;

import com.huishu.ManageServer.entity.dbFirst.Role;
import com.huishu.ManageServer.entity.dbFirst.RolePermission;
import org.springframework.data.repository.CrudRepository;

/**
 * 权限管理
 */

public interface RolePermissionRepository extends CrudRepository<RolePermission, Long>{


    void deleteByRoleId(Role roleId);
}
