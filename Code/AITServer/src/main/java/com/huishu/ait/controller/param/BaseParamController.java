package com.huishu.ait.controller.param;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.alibaba.fastjson.JSONObject;
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
@RequestMapping(value="/apis/param")
public class BaseParamController extends BaseController {
	
	@Autowired
	private ParamService  service;
	
	private static Logger LOGGER = LoggerFactory.getLogger(BaseParamController.class);
	
	//首次进入今日头条，将会给予提示
	@ResponseBody
	@RequestMapping("/getInsertParam.json")
   public AjaxResult getInsertParam(@RequestBody Param param){
		if( param == null){
			 return error(MsgConstant.ILLEGAL_PARAM);
		}
	//	Long userId = getUserId();
		Long userId = (long)2;//测试用 
           
		List<Param> findByUid = service.findByUid(userId);
		if(!findByUid.isEmpty()){
			boolean info = service.deleteParamAllByUid(userId);
			if(!info){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
		}
		boolean b = false;
		String[] msg = param.getMsg();
		List<Param> list = new ArrayList<>();
		for (int i = 0; i < msg.length; i++) {
			Param pa = new Param();
			JSONObject obj = JSONObject.parseObject(msg[i]);
			for (String str : obj.keySet()) {
				pa.setIndustryInfo(str);
			}
			pa.setIndustryLagel(obj.getString(pa.getIndustryInfo()));
			pa.setId(userId);
			list.add(pa);
		}
		try{
			b = service.saveParams(list);
		}catch(Exception e){
			LOGGER.error("存储今日头条爱好条件出错",e);
			return error("存储今日头条爱好条件出错");
		}
		if(!b){
			return error("保存失败！");
		}else{
			List<Param> list2 = service.findByUid(userId);
			return success(list2);
		}
		/*for (Param param : list) {
			param.setUid(userId);
			b = service.insert(param);
			if( !b){
				return error("保存失败！");
			}
		}
		if( b ){
			List<Param> list2 = service.findByUid(userId);
			return success(list2);
		}*/
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

