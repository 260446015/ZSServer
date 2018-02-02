package com.huishu.ManageServer.repository.first;

import com.huishu.ManageServer.entity.dbFirst.AdminBase;
import com.huishu.ManageServer.entity.dbFirst.UserBase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员表dao
 *
 * @author yindq
 * @date 2018/2/2
 */
@Repository
public interface AdminRepository extends CrudRepository<AdminBase, Long> {
	AdminBase findByUserAccount(String userAccount);

}
