package com.huishu.ait.controller.user.backstage;

import com.huishu.ait.common.conf.MsgConstant;
import com.huishu.ait.controller.BaseController;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.entity.dto.CompanySearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台管理-需求池
 *
 * @author yindq
 * @date 2017/9/21
 */
@RestController
@RequestMapping(value = "/apis/back/pool")
public class BackPoolController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(BackPoolController.class);
    //@Autowired
    //private DemandPoolService demandPoolService;

    /**
     * 查看需求池中公司列表
     * @param searchModel
     * @return
     */
    @RequestMapping(value = "getCompanyList.json", method = RequestMethod.POST)
    public AjaxResult getCompanyList(@RequestBody CompanySearchDTO searchModel) {
        if (null==searchModel || null==searchModel.getStatus()) {
            return error(MsgConstant.ILLEGAL_PARAM);
        }
        try {
            //List<GardenDataDTO> list = userParkService.getGardenList(searchModel);
            //return success(changeObject(searchModel, list));
            return null;
        } catch (Exception e) {
            LOGGER.error("getCompanyList查询失败！",e);
            return error(MsgConstant.SYSTEM_ERROR);
        }
    }
}
