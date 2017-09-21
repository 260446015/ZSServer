package com.huishu.ait.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 需求池企业实体类
 *
 * @author yindq
 * @date 2017/9/21
 */
@Entity
@Table(name = "t_pool_company")
public class PoolCompany implements Serializable {
    private static final long serialVersionUID = 7277023251581183895L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** 添加人ID */
    @Column(name="user_id")
    private Long userId;
    /** 添加人园区 */
    @Column(name="user_park")
    private String userPark;
    /**
     * 企业名
     */
    private String name;
    /**
     * 所属企业
     */
    @Column(name="father_name")
    private String fatherName;
    /**
     * 企业标签
     */
    private String label;
    /**
     * 企业关系
     */
    private String relation;
    /**
     * 企业关系备注
     */
    @Column(name="relation_remark")
    private String relationRemark;
    /**
     * 地域
     */
    private String area;
    /**
     * 企业状态
     */
    @Column(name="company_status")
    private String companyStatus;
    /**
     * 负责人
     */
    @Column(name="responsible_person")
    private String responsiblePerson;
    /**
     * 招商状态
     */
    @Column(name="investment_status")
    private String investmentStatus;
    /**
     * 招商备注
     */
    @Column(name="investment_remarks")
    private String investmentRemark;
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private String createTime;


	public String getUserPark() {
		return userPark;
	}

	public void setUserPark(String userPark) {
		this.userPark = userPark;
	}

	public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelationRemark() {
        return relationRemark;
    }

    public void setRelationRemark(String relationRemark) {
        this.relationRemark = relationRemark;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getInvestmentStatus() {
        return investmentStatus;
    }

    public void setInvestmentStatus(String investmentStatus) {
        this.investmentStatus = investmentStatus;
    }

    public String getInvestmentRemark() {
        return investmentRemark;
    }

    public void setInvestmentRemark(String investmentRemark) {
        this.investmentRemark = investmentRemark;
    }
}
