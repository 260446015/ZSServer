package com.huishu.ManageServer.entity.dto;

/**
 * 融资dto
 *
 * @author yindq
 * @date 2018/2/1
 */
public class FinancingDTO {
	private String id;
	/** 公司logo */
	private String logo;
	/** 融资轮次 */
	private String invest;
	/** 融资金额 */
	private String financingAmount;
	/** 融资时间 */
	private String financingDate;
	/** 投资方 */
	private String investor;
	/** 融资公司 */
	private String financingCompany;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getInvest() {
		return invest;
	}

	public void setInvest(String invest) {
		this.invest = invest;
	}

	public String getFinancingAmount() {
		return financingAmount;
	}

	public void setFinancingAmount(String financingAmount) {
		this.financingAmount = financingAmount;
	}

	public String getFinancingDate() {
		return financingDate;
	}

	public void setFinancingDate(String financingDate) {
		this.financingDate = financingDate;
	}

	public String getInvestor() {
		return investor;
	}

	public void setInvestor(String investor) {
		this.investor = investor;
	}

	public String getFinancingCompany() {
		return financingCompany;
	}

	public void setFinancingCompany(String financingCompany) {
		this.financingCompany = financingCompany;
	}
}
