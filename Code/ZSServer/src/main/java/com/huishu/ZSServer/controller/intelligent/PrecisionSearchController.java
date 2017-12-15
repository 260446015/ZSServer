package com.huishu.ZSServer.controller.intelligent;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishu.ZSServer.common.AjaxResult;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.controller.BaseController;
import com.huishu.ZSServer.entity.IndusCompany;
import com.huishu.ZSServer.entity.dto.SearchDTO;
import com.huishu.ZSServer.service.company.EnterPriseService;
import com.huishu.ZSServer.service.company.IndusCompanyService;

/**
 * @author hhy
 * @date 2017年11月30日
 * @Parem
 * @return 
 * 精确筛选相关controller
 */
@Controller
@RequestMapping("/accurateScreening")
public class PrecisionSearchController extends BaseController{
	private Logger LOGGER = LoggerFactory.getLogger(PrecisionSearchController.class);
	@Autowired
	private EnterPriseService service;
	@Autowired
	private  IndusCompanyService  iservice;
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "/search/"+page;
	}
	@ResponseBody
	@RequestMapping(value="/getCompanyInfoBySearch.json",method=RequestMethod.POST)
	public AjaxResult getCompanyInfoBySearch(@RequestBody SearchDTO  dto){
		if(dto==null){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		String industry =dto.getIndustry();
		String money = dto.getRegister();
		String time = dto.getRegisterTime();
		String area = dto.getArea();
		if(area.equals("全部")&&industry.equals("全部")&&time.equals("全部")&&money.equals("全部")){
			Iterable<IndusCompany> list = iservice.listCompany();
			return success(list);
		}else{
			if(area.equals("全部")){
				area = "%%";
			}else{
				area = "%"+area+"%";
			}
			if(industry.equals("全部")){
				industry = "%%";
			}else{
				industry="%"+industry+"%";
			}
			
			List<IndusCompany> info = service.getCompanyList(industry,money,time,area);
			if(info==null){
				return error("暂无数据");
			}
			return success(info);
		}
	}
}
