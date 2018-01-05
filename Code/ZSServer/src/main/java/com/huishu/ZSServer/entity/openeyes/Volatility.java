package com.huishu.ZSServer.entity.openeyes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yindawei
 * @date 2017年12月4日下午6:48:03
 * @description 股票行情
 * @version
 */
@Table(name = "t_volatility")
@Entity
public class Volatility implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7076100265069758300L;

	@Id
	private String id;
	/** 公司id */
	private Long graphId;
	/** 股票号 */
	private String stockcode;
	/** 股票名 */
	private String stockname;
	/** 股票类型 */
	private String stockType;
	/** 更新时间 */
	private String timeshow;
	/** 市盈率（动） */
	private String fvaluep;
	/** 总市值 */
	private String tvalue;
	/** 流通市值 */
	private String flowvalue;
	/** 市净率 */
	private String tvaluep;
	/** 今开 */
	private String topenprice;
	/** 成交量 */
	private String tamount;
	/** 振幅 */
	private String trange;
	/** 最⾼ */
	private String thighprice;
	/** 成交额 */
	private String tamounttotal;
	/** 换⼿ */
	private String tchange;
	/** 最低 */
	private String tlowprice;
	/** 昨收 */
	private String pprice;
	/** 涨停 */
	private String tmaxprice;
	/** 跌停 */
	private String tminprice;
	/** 公司名称 */
	@Column(name = "hexm_curPrice")
	private String hexm_curPrice;
	/** 域名 */
	private String hexmfloatprice;
	/** 跌停 */
	private String hexmfloatrate;
	/** 公司名称 */
	private String stop;
	/** 域名 */
	private String flag;
	/** 所属公司 */
	private String cname;
	private Long creationTime;

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getGraphId() {
		return graphId;
	}

	public void setGraphId(Long graphId) {
		this.graphId = graphId;
	}

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getTimeshow() {
		return timeshow;
	}

	public void setTimeshow(String timeshow) {
		this.timeshow = timeshow;
	}

	public String getFvaluep() {
		return fvaluep;
	}

	public void setFvaluep(String fvaluep) {
		this.fvaluep = fvaluep;
	}

	public String getTvalue() {
		return tvalue;
	}

	public void setTvalue(String tvalue) {
		this.tvalue = tvalue;
	}

	public String getFlowvalue() {
		return flowvalue;
	}

	public void setFlowvalue(String flowvalue) {
		this.flowvalue = flowvalue;
	}

	public String getTvaluep() {
		return tvaluep;
	}

	public void setTvaluep(String tvaluep) {
		this.tvaluep = tvaluep;
	}

	public String getTopenprice() {
		return topenprice;
	}

	public void setTopenprice(String topenprice) {
		this.topenprice = topenprice;
	}

	public String getTamount() {
		return tamount;
	}

	public void setTamount(String tamount) {
		this.tamount = tamount;
	}

	public String getTrange() {
		return trange;
	}

	public void setTrange(String trange) {
		this.trange = trange;
	}

	public String getThighprice() {
		return thighprice;
	}

	public void setThighprice(String thighprice) {
		this.thighprice = thighprice;
	}

	public String getTamounttotal() {
		return tamounttotal;
	}

	public void setTamounttotal(String tamounttotal) {
		this.tamounttotal = tamounttotal;
	}

	public String getTchange() {
		return tchange;
	}

	public void setTchange(String tchange) {
		this.tchange = tchange;
	}

	public String getTlowprice() {
		return tlowprice;
	}

	public void setTlowprice(String tlowprice) {
		this.tlowprice = tlowprice;
	}

	public String getPprice() {
		return pprice;
	}

	public void setPprice(String pprice) {
		this.pprice = pprice;
	}

	public String getTmaxprice() {
		return tmaxprice;
	}

	public void setTmaxprice(String tmaxprice) {
		this.tmaxprice = tmaxprice;
	}

	public String getTminprice() {
		return tminprice;
	}

	public void setTminprice(String tminprice) {
		this.tminprice = tminprice;
	}

	public String getHexm_curPrice() {
		return hexm_curPrice;
	}

	public void setHexm_curPrice(String hexm_curPrice) {
		this.hexm_curPrice = hexm_curPrice;
	}

	public String getHexmfloatprice() {
		return hexmfloatprice;
	}

	public void setHexmfloatprice(String hexmfloatprice) {
		this.hexmfloatprice = hexmfloatprice;
	}

	public String getHexmfloatrate() {
		return hexmfloatrate;
	}

	public void setHexmfloatrate(String hexmfloatrate) {
		this.hexmfloatrate = hexmfloatrate;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
