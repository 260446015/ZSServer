package com.huishu.ManageServer.service.user;

import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.vo.UserLogoVO;
import org.springframework.data.domain.Page;

/**
 * 账户监控
 *
 * @author yindq
 * @date 2018/1/11
 */
public interface UserMonitorService {
	/**
	 * 分页查看用户日志列表
	 * @param dto
	 * @return
	 */
	Page<UserLogoVO> listLogo(Integer logoType, AbstractDTO dto);
}
