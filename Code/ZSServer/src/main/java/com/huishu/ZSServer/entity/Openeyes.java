package com.huishu.ZSServer.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author yindawei 
 * @date 2017年11月1日下午3:49:12
 * @description 天眼查pojo
 * @version
 */
public class Openeyes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 544452536886282650L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	

}
