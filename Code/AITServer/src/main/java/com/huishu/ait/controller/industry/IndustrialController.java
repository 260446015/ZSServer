package com.huishu.ait.controller.industry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.huishu.ait.entity.IndustrialPolicy;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.IndustrialPolicyDTO;
import com.huishu.ait.es.entity.AITInfo;
import com.huishu.ait.service.industrialPolicy.IndustrialPolicyService;
import com.alibaba.fastjson.JSONArray;
import com.huishu.ait.common.conf.ConfConstant;
import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.ConcersUtils.DateUtil;
import com.huishu.ait.controller.BaseController;

/**
 * 产业政策相关接口
 * @author jdz
 * @version 1.0Bate
 * @CreateTime 2017-7-27 17:18:16
 */
@Controller
@RequestMapping(value="industry")
public class IndustrialController extends BaseController {

    //加载日志
    private static Logger log = LoggerFactory.getLogger(IndustrialController.class);
    
    @Autowired      //产业政策service
    private IndustrialPolicyService industrialPolicyService;
    
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
            dto.setPageSize(15);
        }
        if(dto.getPageSize()>1000){
            dto.setPageSize(1000);
        }
        return dto;
    }
    /**
     * 获取产业政策列表接口
     * @param IndustrialPolicyDTO 产业政策查询对象
     * @return AjaxResult 返回一个对象，里面是数据
     */
    @RequestMapping(value="getIndustrialPolicyList.json", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult getIndustrialPolicyList(IndustrialPolicyDTO dto ){
        try{
            
            pageInit(dto);
            Boolean b = checkPolicyDTO(dto);
            if(b == true){
                /** 创建一个 indusPolList对象，用于存储产业政策文章列表 */
                Page<AITInfo> pagedata = industrialPolicyService.getIndustrialPolicyList(dto);
                return success(pagedata).setSuccess(true);
            }
            else{
                return error(MsgConstant.ILLEGAL_PARAM);
            }
        }catch(Exception e){
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
}
