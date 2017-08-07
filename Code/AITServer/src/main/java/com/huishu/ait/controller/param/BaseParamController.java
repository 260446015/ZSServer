package com.huishu.ait.controller.param;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.Param;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.service.param.ParamService;
import com.huishu.ait.common.conf.ConfConstant;
import com.huishu.ait.common.conf.ConfConstant.INDUSTRY;
/**
 * @author hhy
 * @date 2017年8月3日
 * @Parem
 * @return 
 * 本方法主要针对公用参数的添加与删除
 */
@Controller
@RequestMapping(value="/param")
public class BaseParamController extends BaseController {
	
	@Autowired
	private ParamService  service;
	
	//首次进入今日头条，将会给予提示
	@ResponseBody
	@RequestMapping("/getInsertParam.json")
   public AjaxResult getInsertParam(@RequestBody List<Param>  list){
		if(list.isEmpty()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
//		Long userId = getUserId();
		Long userId = (long)2;//测试用
           
		List<Param> findByUid = service.findByUid(userId);
		if(!findByUid.isEmpty()){
			boolean info = service.deleteParamAllByUid(userId);
			if(!info){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
		}
		boolean b = false;
		for (Param param : list) {
			param.setUid(userId);
			b = service.insert(param);
			if( !b){
				return error("保存失败！");
			}
		}
		if( b ){
			List<Param> list2 = service.findByUid(userId);
			return success(list2);
		}
		return error(MsgConstant.ILLEGAL_PARAM);
	}
	/**
	 * 根据当前的用户id 查询所有的参数信息--用于页面跳转时使用
	 * @param uid
	 * @return
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping("/getParamById.json")
	public AjaxResult  getParamById(){
//		Long userId = getUserId();
		Long userId = (long)2;//测试用
		if(userId == null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		return success(service.findByUid(userId));
	}
	
}

