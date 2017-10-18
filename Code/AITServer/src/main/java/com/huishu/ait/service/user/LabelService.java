package com.huishu.ait.service.user;

import java.util.List;

import com.huishu.ait.entity.Label;
import com.huishu.ait.entity.common.AjaxResult;

/**
 * 需求池用户标签
 * 
 * @author yindq
 * @create 2017年9月22日
 */
public interface LabelService {
	/**
	 * 查看企业标签
	 * @Label park
	 * @return
	 */
	List<Label> getLabel(String park);
	
	/**
	 * 查看我的企业标签
	 * @Label id
	 * @return
	 */
	List<Label> getMyLabel(Long id);
	
	/**
	 * 添加标签
	 * @Label label
	 * @return
	 */
	AjaxResult addLabel(Label label);
	
	/**
	 * 删除标签
	 * @Label msg
	 * @return
	 */
	AjaxResult dropLabel(String msg[]);
}
