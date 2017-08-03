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
	
	/**互联网下的产业标签*/
	 String  [] industryLagel1 = {"网络游戏","大数据","电子商务","网络视听","移动阅读","智能硬件"};
	
	/**高科技下的产业标签*/
	 String  [] industryLagel2 = {"新一代信息技术","智能机器人","生物医药","节能环保技术装备","新能源","新材料","航空装备"};
	
	/**文化创意下的产业标签*/
	 String  [] industryLagel3 = {"动漫制作","影视传媒","图书出版","广告营销"};

	/**精英配套下的产业标签*/
	 String  [] industryLagel4 = {"金融服务","住宅地产","商业综合体","康体美容","母婴产业","健康产业","教育培训"};
	
	@Autowired
	private ParamService  service;
	
	//首次进入今日头条，将会给予提示
	@ResponseBody
	@RequestMapping("/getInsertParam.json")
   public AjaxResult getInsertParam(@RequestBody List<Param>  list){
		if(list.isEmpty()){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		Long uid = (long) 2;
		for(Param pm :list){
			Long Iid = pm.getIid();
			Param info = new Param();
			int m = service.fingOne(uid);
			if(m==1){
				boolean b = service.deleteParamAllByUid(uid);
				if(!b){
					return error("参数不合法");
				}
			}
			if( Iid == 0){
				info.setIid(Iid);
				info.setIndustryInfo(INDUSTRY.FIRST_industry);
				String lagel = pm.getIndustryLagel();
				String[] split = lagel.split(",");
				String str = null;
			    for(int i = 0; i < split.length;i++){
			    	str = industryLagel1[i]+",";
			    	str += str;
			    }	
			    info.setIndustryLagel(str);
			   boolean b =  service.insert(info);
			   if(! b ){
				   return error("参数不合法");
			   }
			}
			if( Iid == 1){
				info.setIid(Iid);
				info.setIndustryInfo(INDUSTRY.FIRST_industry);
				String lagel = pm.getIndustryLagel();
				String[] split = lagel.split(",");
				String str = null;
				for(int i = 0; i < split.length;i++){
					str = industryLagel2[i]+",";
					str += str;
				}	
				info.setIndustryLagel(str);
				boolean b =  service.insert(info);
				if(! b ){
					return error("参数不合法");
				}
			}
			if( Iid == 2){
				info.setIid(Iid);
				info.setIndustryInfo(INDUSTRY.FIRST_industry);
				String lagel = pm.getIndustryLagel();
				String[] split = lagel.split(",");
				String str = null;
				for(int i = 0; i < split.length;i++){
					str = industryLagel3[i]+",";
					str += str;
				}	
				info.setIndustryLagel(str);
				boolean b =  service.insert(info);
				if(! b ){
					return error("参数不合法！");
				}
			}
			if( Iid == 3){
				info.setIid(Iid);
				info.setIndustryInfo(INDUSTRY.FIRST_industry);
				String lagel = pm.getIndustryLagel();
				String[] split = lagel.split(",");
				String str = null;
				for(int i = 0; i < split.length;i++){
					str = industryLagel4[i]+",";
					str += str;
				}	
				info.setIndustryLagel(str);
				boolean b =  service.insert(info);
				if(! b ){
					return error("参数不合法");
				}
			}
		} 
		return success(service.fingOneById(uid));
	}	
}

