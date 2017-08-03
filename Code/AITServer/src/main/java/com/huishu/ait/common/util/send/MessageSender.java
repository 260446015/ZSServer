package com.huishu.ait.common.util.send;

import com.huishu.ait.entity.common.AjaxResult;

/**
 * 消息发送器
 * @author yuwei 
 *
 */
public interface MessageSender {
	
	/**
	 * 发送消息
	 * @param msg
	 * @return
	 */
	AjaxResult send(String msg, String to);

}
