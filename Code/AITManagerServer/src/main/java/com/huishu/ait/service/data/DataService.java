package com.huishu.ait.service.data;

import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.es.entity.AITInfo;

/**
 * 管理文章数据
 * @author yindq
 * @date 2017/10/25
 */
public interface DataService {
	/**
     * 添加文章
     * @param info
     * @return
     */
    AjaxResult addData(AITInfo info);
    /**
     * 将字符串转化为相应的对象
     * @param value
     * @return
     */
    AITInfo transformData(String value);
    /**
     * 校验excel表头是否正确
     * @param header
     * @return
     */
    Boolean checkString(String header);
    /**
     * 添加数据日志
     * @param name
     * @param message
     * @return
     */
    Boolean printLog(String name,String message);
}
