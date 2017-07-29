package com.huishu.ait.controller;

import static com.huishu.ait.common.util.UtilsHelper.getValueByFieldName;

import java.util.List;

/*import org.apache.shiro.SecurityUtils;*/
//import org.springframework.beans.factory.annotation.Autowired;

import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.dto.AbstractDTO;
import com.huishu.ait.common.util.CheckUtils;



public abstract class BaseController {
	
	
	
	public AjaxResult success(Object data) {
		return new AjaxResult().setData(data).setSuccess(true).setStatus(0);
	}
	
	public AjaxResult error(String message) {
		return new AjaxResult().setMessage(message).setSuccess(false).setStatus(1);
	}

//	public ShiroUser getCurrentShiroUser() {
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		return user;
//	}
//	
//	public Long getUserId() {
//		return getCurrentShiroUser().getId();
//	}
//	
//	public String getUserAccount() {
//		return getCurrentShiroUser().getLoginName();
//	}
//	
    public boolean  checkDTO(AbstractDTO dto ){
    	if ( dto == null ){
    		return false;
    	}
    	
    	List<String> fieldNames = dto.getFieldNames();
    	if(fieldNames.contains("industry")){
    		Object industry = getValueByFieldName(dto, "industry");
    	   if(industry == null){
    		   return false;
    	   }
    	}
    	if(fieldNames.contains("industryLabel")){
    		Object industryLabel = getValueByFieldName(dto, "industryLabel");
    		if(industryLabel == null){
    			return false;
    		}
    	}
    	if(fieldNames.contains("vector")){
    		Object vector = getValueByFieldName(dto, "vector");
    		if(vector == null){
    			return false;
    		}
    	}
    	if(fieldNames.contains("keyword")){
    		Object keyword = getValueByFieldName(dto, "keyword");
    		if(keyword == null){
    			return false;
    		}
    	}
    	
    	if (fieldNames.contains("startDate")) {
			Object startDate = getValueByFieldName(dto, "startDate");
			
			if (startDate != null) {
				if (!CheckUtils.checkDateTime(startDate.toString())) {
					return false;
				}
			}
		}
		
		if (fieldNames.contains("endDate")) {
			Object endDate = getValueByFieldName(dto, "endDate");
			
			if (endDate != null) {
				if (!CheckUtils.checkDateTime(endDate.toString())) {
					return false;
				}
			}
			
		}
    	return true;
    }
}
