package com.huishu.ait.controller.user;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.common.util.StringUtil;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.PoolCompany;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanySearchDTO;
import com.huishu.ait.service.user.DemandPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 招商需求池
 *
 * @author yindq
 * @date 2017/9/21
 */
@RestController
@RequestMapping(value = "/apis/pool")
public class DemandPoolController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(DemandPoolController.class);
    @Autowired
    private DemandPoolService demandPoolService;

    /**
     * 查看需求池公司列表---领导
     * @param searchModel
     * @return
     */
    @RequestMapping(value = "getCompanyList.json", method = RequestMethod.POST)
    public AjaxResult getCompanyList(@RequestBody CompanySearchDTO searchModel) {
        if (null==searchModel ||null==searchModel.getMsg()||3!=searchModel.getMsg().length) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }
        try {
            searchModel.setPark(getUserPark());
            List<PoolCompany> list = demandPoolService.getCompanyList(searchModel);
            return success(changeObject(searchModel, list));
        } catch (Exception e) {
            LOGGER.error("getMyCompanyList查询失败！",e);
            return error(MsgConstant.SYSTEM_ERROR);
        }
    }
    
    /**
     * 查看我的需求池公司列表
     * @param searchModel
     * @return
     */
    @RequestMapping(value = "getMyCompanyList.json", method = RequestMethod.POST)
    public AjaxResult getMyCompanyList(@RequestBody CompanySearchDTO searchModel) {
        if (null==searchModel ||null==searchModel.getMsg()||3!=searchModel.getMsg().length) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }
        try {
            searchModel.setUserId(getUserId());
            List<PoolCompany> list = demandPoolService.getMyCompanyList(searchModel);
            return success(changeObject(searchModel, list));
        } catch (Exception e) {
            LOGGER.error("getMyCompanyList查询失败！",e);
            return error(MsgConstant.SYSTEM_ERROR);
        }
    }

    /**
     * 需求池添加需求
     * @param poolCompany
     * @return
     */
    @RequestMapping(value = "addPoolCompany.json", method = RequestMethod.POST)
    public AjaxResult addPoolCompany(@RequestBody PoolCompany poolCompany) {
        if (null==poolCompany || StringUtil.isEmpty(poolCompany.getName())) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }
        try {
            poolCompany.setUserId(getUserId());
            poolCompany.setUserPark(getUserPark());
            return demandPoolService.addPoolCompany(poolCompany);
        } catch (Exception e) {
            LOGGER.error("addPoolCompany查询失败！",e);
            return error(MsgConstant.SYSTEM_ERROR);
        }
    }
}
