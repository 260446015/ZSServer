package com.huishu.ait.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 园区数据实体类
 * @author jdz
 * @version 1.0
 * @createDate 2017-8-18
 */
@Entity
@Table(name = "t_garden_data")
public class GardenData implements Serializable{

    private static final long serialVersionUID = 333324299374920031L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "garden_name") /**园区名称 */
    private String gardenName;
    @Column(name = "garden_new_name") /**园区新名称*/
    private String gardenNewName;
    @Column(name = "area") /**区域位置  */
    private String area;
    @Column(name = "garden_website") /**园区网址  */
    private String gardenWebsite;
    @Column(name = "address") /**园区地址  */
    private String address;
    @Column(name = "garden_level") /**园区等级  */
    private String gardenLevel;
    @Column(name = "establish_Date") /**成立时间  */
    private String establishDate;
    @Column(name = "property_type") /**产权类别  */
    private String propertyType;
    @Column(name = "leading_industry") /**主导产业  */
    private String leadingIndustry;
    @Column(name = "garden_type") /**园区类别  */
    private String gardenType;
    @Column(name = "infrastructure") /**基础设施  */
    private String infrastructure;
    @Column(name = "garden_square") /**园区面积  */
    private String gardenSquare;
    @Column(name = "garden_operator") /**运营商  */
    private String gardenOperator;
    @Column(name = "land_price") /**土地价格  */
    private String landPrice;
    @Column(name = "investment_intensity") /**投资强度  */
    private String investmentIntensity;
    @Column(name = "tax_requirements") /**税收要求  */
    private String taxRequirements;
    @Column(name = "environment") /**自然环境  */
    private String environment;
    @Column(name = "garden_introduce") /**园区介绍  */
    private String gardenIntroduce;
    @Column(name = "garden_superiority") /**园区优势  */
    private String gardenSuperiority;
    @Column(name = "supporting_facilities") /**配套设施  */
    private String supportingFacilities;
    @Column(name = "garden_plan") /**园区规划  */
    private String gardenPlan;
    @Column(name = "garden_policy") /**园区政策  */
    private String gardenPolicy;
    @Column(name = "garden_picture") /**园区图片  */
    private String gardenPicture;
    @Column(name = "remarks") /**备注  */
    private String remarks;
    
    public GardenData() {
        super();
    }
    public GardenData(int id, String gardenName, String gardenNewName, String area, String gardenWebsite,
            String address, String gardenLevel, String establishDate, String propertyType, String leadingIndustry,
            String gardenType, String infrastructure, String gardenSquare, String gardenOperator, String landPrice,
            String investmentIntensity, String taxRequirements, String environment, String gardenIntroduce,
            String gardenSuperiority, String supportingFacilities, String gardenPlan, String gardenPolicy,
            String gardenPicture, String remarks) {
        super();
        this.id = id;
        this.gardenName = gardenName;
        this.gardenNewName = gardenNewName;
        this.area = area;
        this.gardenWebsite = gardenWebsite;
        this.address = address;
        this.gardenLevel = gardenLevel;
        this.establishDate = establishDate;
        this.propertyType = propertyType;
        this.leadingIndustry = leadingIndustry;
        this.gardenType = gardenType;
        this.infrastructure = infrastructure;
        this.gardenSquare = gardenSquare;
        this.gardenOperator = gardenOperator;
        this.landPrice = landPrice;
        this.investmentIntensity = investmentIntensity;
        this.taxRequirements = taxRequirements;
        this.environment = environment;
        this.gardenIntroduce = gardenIntroduce;
        this.gardenSuperiority = gardenSuperiority;
        this.supportingFacilities = supportingFacilities;
        this.gardenPlan = gardenPlan;
        this.gardenPolicy = gardenPolicy;
        this.gardenPicture = gardenPicture;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getGardenName() {
        return gardenName;
    }
    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }
    public String getGardenNewName() {
        return gardenNewName;
    }
    public void setGardenNewName(String gardenNewName) {
        this.gardenNewName = gardenNewName;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getGardenWebsite() {
        return gardenWebsite;
    }
    public void setGardenWebsite(String gardenWebsite) {
        this.gardenWebsite = gardenWebsite;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getGardenLevel() {
        return gardenLevel;
    }
    public void setGardenLevel(String gardenLevel) {
        this.gardenLevel = gardenLevel;
    }
    public String getEstablishDate() {
        return establishDate;
    }
    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }
    public String getPropertyType() {
        return propertyType;
    }
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    public String getLeadingIndustry() {
        return leadingIndustry;
    }
    public void setLeadingIndustry(String leadingIndustry) {
        this.leadingIndustry = leadingIndustry;
    }
    public String getGardenType() {
        return gardenType;
    }
    public void setGardenType(String gardenType) {
        this.gardenType = gardenType;
    }
    public String getInfrastructure() {
        return infrastructure;
    }
    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }
    public String getGardenSquare() {
        return gardenSquare;
    }
    public void setGardenSquare(String gardenSquare) {
        this.gardenSquare = gardenSquare;
    }
    public String getGardenOperator() {
        return gardenOperator;
    }
    public void setGardenOperator(String gardenOperator) {
        this.gardenOperator = gardenOperator;
    }
    public String getLandPrice() {
        return landPrice;
    }
    public void setLandPrice(String landPrice) {
        this.landPrice = landPrice;
    }
    public String getInvestmentIntensity() {
        return investmentIntensity;
    }
    public void setInvestmentIntensity(String investmentIntensity) {
        this.investmentIntensity = investmentIntensity;
    }
    public String getTaxRequirements() {
        return taxRequirements;
    }
    public void setTaxRequirements(String taxRequirements) {
        this.taxRequirements = taxRequirements;
    }
    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    public String getGardenIntroduce() {
        return gardenIntroduce;
    }
    public void setGardenIntroduce(String gardenIntroduce) {
        this.gardenIntroduce = gardenIntroduce;
    }
    public String getGardenSuperiority() {
        return gardenSuperiority;
    }
    public void setGardenSuperiority(String gardenSuperiority) {
        this.gardenSuperiority = gardenSuperiority;
    }
    public String getSupportingFacilities() {
        return supportingFacilities;
    }
    public void setSupportingFacilities(String supportingFacilities) {
        this.supportingFacilities = supportingFacilities;
    }
    public String getGardenPlan() {
        return gardenPlan;
    }
    public void setGardenPlan(String gardenPlan) {
        this.gardenPlan = gardenPlan;
    }
    public String getGardenPolicy() {
        return gardenPolicy;
    }
    public void setGardenPolicy(String gardenPolicy) {
        this.gardenPolicy = gardenPolicy;
    }
    public String getGardenPicture() {
        return gardenPicture;
    }
    public void setGardenPicture(String gardenPicture) {
        this.gardenPicture = gardenPicture;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    @Override
    public String toString() {
        return "GardenData [id=" + id + ", gardenName=" + gardenName + ", gardenNewName=" + gardenNewName + ", area="
                + area + ", gardenWebsite=" + gardenWebsite + ", address=" + address + ", gardenLevel=" + gardenLevel
                + ", establishDate=" + establishDate + ", propertyType=" + propertyType + ", leadingIndustry="
                + leadingIndustry + ", gardenType=" + gardenType + ", infrastructure=" + infrastructure
                + ", gardenSquare=" + gardenSquare + ", gardenOperator=" + gardenOperator + ", landPrice=" + landPrice
                + ", investmentIntensity=" + investmentIntensity + ", taxRequirements=" + taxRequirements
                + ", environment=" + environment + ", gardenIntroduce=" + gardenIntroduce + ", gardenSuperiority="
                + gardenSuperiority + ", supportingFacilities=" + supportingFacilities + ", gardenPlan=" + gardenPlan
                + ", gardenPolicy=" + gardenPolicy + ", gardenPicture=" + gardenPicture + ", remarks=" + remarks + "]";
    }
}
