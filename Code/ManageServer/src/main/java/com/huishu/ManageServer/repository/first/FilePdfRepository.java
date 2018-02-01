package com.huishu.ManageServer.repository.first;

import com.huishu.ManageServer.entity.dbFirst.FilePdf;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * PDF文件
 * 
 * @author yindq
 * @date 2017年11月9日
 */
@Repository
public interface FilePdfRepository extends CrudRepository<FilePdf, Long> ,JpaSpecificationExecutor<FilePdf> {

}
