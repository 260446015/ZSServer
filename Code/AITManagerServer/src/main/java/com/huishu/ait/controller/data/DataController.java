package com.huishu.ait.controller.data;

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

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.Log;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.service.data.DataService;

/**
 * 数据管理模块
 * 
 * @author yindq
 * @create 2017年10月24日
 */
@Controller
@RequestMapping(value = "/apis/data")
public class DataController extends BaseController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);
	@Autowired
    private DataService dataService;
	/**
	 * 直接跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String show(@PathVariable String page) {
		return "data/"+page;
	}
	
	/**
	 * 手动添加数据
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "addData.json", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addData(@RequestBody AITInfo info) {
		try {
            return dataService.addData(info);
        } catch (Exception e) {
            LOGGER.error("addData失败！",e);
            return error(MsgConstant.SYSTEM_ERROR);
        }
	}
	
	/**
	 * 数据操作日志
	 * @return
	 */
	@RequestMapping(value = "getOperationLogList.json", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getOperationLogList() {
		try {
			List<Log> list = dataService.getOperationLogList();
			return success(list);
        } catch (Exception e) {
            LOGGER.error("operationLogList查询失败！",e);
            return error(MsgConstant.SYSTEM_ERROR);
        }
	}
}
