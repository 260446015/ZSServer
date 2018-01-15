package com.huishu.ManageServer.service.user;


import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dbFirst.UserPark;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.AccurateDTO;
import com.huishu.ManageServer.entity.vo.GardenDataVO;
import org.springframework.data.domain.Page;

/**
 * 后台管理-园区管理
 * 
 * @author yindq
 * @create 2018/1/15
 */
public interface UserParkService {
	/**
	 * 查看园区分页列表
	 * 
	 * @param dto
	 *            查询条件
	 * @return
	 */
	Page<GardenDataVO> getGardenList(AbstractDTO dto);

	/**
	 * 添加园区
	 * 
	 * @param userPark
	 * @return
	 */
	Boolean addGarden(UserPark userPark);

	/**
	 * 查看园区信息
	 * 
	 * @param id
	 * @return
	 */
	UserPark findParkInformation(Long id);

	/**
	 * 查看园区账号列表
	 * 
	 * @param dto
	 * @return
	 */
	Page<UserBase> findParkAccount(AccurateDTO dto);

}
