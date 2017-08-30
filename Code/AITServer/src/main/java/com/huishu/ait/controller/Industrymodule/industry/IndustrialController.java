package com.huishu.ait.controller.Industrymodule.industry;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils.DateUtil;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.IndustrialPolicyDTO;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;

/**
 * 产业政策相关接口
 * @author jdz
 * @version 1.0Bate
 * @CreateTime 2017-7-27 17:18:16
 */
@Controller
@RequestMapping(value="/apis/industry")
public class IndustrialController extends BaseController {

    //加载日志
    private static Logger log = LoggerFactory.getLogger(IndustrialController.class);
    
    @Autowired      //产业政策service
    private IndustrialPolicyService industrialPolicyService;
    
    /**
     * 获取产业政策列表接口
     * @param IndustrialPolicyDTO 产业政策查询对象
     * @return AjaxResult 返回一个对象，里面是数据
     */
    @RequestMapping(value="getIndustrialPolicyList.json", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult getIndustrialPolicyList(@RequestBody IndustrialPolicyDTO dto){
        try{
            if (null == dto || 3!=dto.getMsg().length){
                return error(MsgConstant.ILLEGAL_PARAM);
            }
            String[] labels = dto.getMsg();

            if (!StringUtil.isEmpty(labels[0])) {
                dto.setIndustry(labels[0]);
            }
            if (!StringUtil.isEmpty(labels[1])) {
                dto.setIndustryLabel(labels[1]);
            }
            if (!StringUtil.isEmpty(labels[2])) {
                dto.setArea(labels[2]);
            }
            JSONArray array = new JSONArray();
            
            dto = pageInit(dto);
                /** 创建一个 indusPolList对象，用于存储产业政策文章列表 */
            array = industrialPolicyService.getIndustrialPolicyList(dto);
            return success(array).setSuccess(true);
        } catch(Exception e) {
            log.error("获取产业政策列表失败："+e.getMessage());
            return error("获取产业政策列表失败"); 
        }
    }
    /**
     * 获取产业政策详情
     * @param id 产业政策id
     * @return
     */
    @RequestMapping(value="getIndustrialPolicyDetailById.json", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getIndustrialPolicyDetailById(String id){
        try{
            if(id!="" || id!=null){
                return success(industrialPolicyService.getIndustrialPolicyDetailById(id));
            }else{
                return error(MsgConstant.ILLEGAL_PARAM);
            }
        }catch(Exception e){
            log.error("获取产业政策详情失败："+e.getMessage());
            return error("获取产业政策详情失败");    
        }
    }
    
    /**
     * 分页初始处理
     */
    private IndustrialPolicyDTO pageInit(IndustrialPolicyDTO dto){
        /*
         * 如果没传入当前页，将当前页设定为0
         * 如果没有单页最大数据量，将数据量设定为15
         * 如果单页数据量大于1000，将其设定为1000
         */
        if(dto.getPageNumber()==null){
            dto.setPageNumber(0);
        }
        if(dto.getPageSize()==null){
            dto.setPageSize(10);
        }
        if(dto.getPageSize()>1000){
            dto.setPageSize(1000);
        }
        return dto;
    }
}
