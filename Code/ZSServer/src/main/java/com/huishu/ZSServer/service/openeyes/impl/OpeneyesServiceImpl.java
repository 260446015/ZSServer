package com.huishu.ZSServer.service.openeyes.impl;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.util.HttpUtils;
import com.huishu.ZSServer.entity.Company;
import com.huishu.ZSServer.entity.CompanyAttation;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.repository.company.CompanyAttaRepository;
import com.huishu.ZSServer.repository.company.CompanyRepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.openeyes.OpeneyesService;

@Service
public class OpeneyesServiceImpl<T> extends AbstractService<T> implements OpeneyesService {

    private static Logger log = LoggerFactory.getLogger(OpeneyesServiceImpl.class);
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyAttaRepository companyAttaRepository;
    private Document document;

    @Override
    public JSONObject getStaffInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "staff");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getBaseInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("method", "baseInfo");
        params.put("tag", dto.getUserId());
    
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        JSONObject openEyesTarget = JSONObject.parseObject(sendHttpGet);
        if (openEyesTarget.size() == 1 || openEyesTarget.getInteger("error_code") != 0) {
            return openEyesTarget;
        }
        CompanyAttation ca = companyAttaRepository.findByCompanyIdAndUserId(openEyesTarget.getLong("id"), dto.getUserId());
        openEyesTarget.getJSONObject("result").put("isAttation", false);
        if (ca != null) {
            openEyesTarget.getJSONObject("result").put("isAttation", true);
        }
        JSONObject jsonObj = openEyesTarget.getJSONObject("result");
        if (dto.getFlag() != null) {
            if (dto.getFlag())
                getJingPin(dto);//此处调用获取竞品信息的功能，存入数据库
        }
        Company company = companyRepository.findByCompanyName(dto.getCname());
        if (null != company) {
            company.setAddress(jsonObj.getString("regLocation"));
            company.setBoss(jsonObj.getString("legalPersonName"));
            company.setEngageState(jsonObj.getString("regStatus"));
            company.setOpenIndustry(jsonObj.getString("industry"));
            company.setLogo(jsonObj.getString("websiteList"));
            company.setOpenRegisterCapital(jsonObj.getString("regCapital"));
            company.setOpenActualCapital(jsonObj.getString("actualCapital"));
            company.setOpenBase(jsonObj.getString("base"));
            company.setOpenBusinessScope(jsonObj.getString("businessScope"));
            company.setOpenCategoryScore(jsonObj.getInteger("categoryScore"));
            company.setOpenCompanyOrgType(jsonObj.getString("companyOrgType"));
            company.setOpenCorrectCompanyId(jsonObj.getString("correctCompanyId"));
            company.setOpenCreditCode(jsonObj.getString("creditCode"));
            company.setOpenEstiblishTime(jsonObj.getLong("estiblishTime"));
            company.setOpenFromTime(jsonObj.getLong("fromTime"));
            company.setOpenLegalPersonId(jsonObj.getLong("legalPersonId"));
            company.setOpenLegalPersonName(jsonObj.getString("legalPersonName"));
            company.setOpenOrgApprovedInstitute(jsonObj.getString("orgApprovedInstitute"));
            company.setOpenOrgNumber(jsonObj.getString("orgNumber"));
            company.setOpenPercentileScore(jsonObj.getIntValue("percentileScore"));
            company.setOpenPhoneNumber(jsonObj.getString("phoneNumber"));
            company.setOpenRegInstitute(jsonObj.getString("regInstitute"));
            company.setOpenRegNumber(jsonObj.getString("regNumber"));
            company.setOpenToTime(jsonObj.getLong("toTime"));
            company.setOpenType(jsonObj.getInteger("type"));
            companyRepository.save(company);
        }
        return openEyesTarget;
    }

    @Override
    public JSONObject getBranch(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "branch");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getHistoryRongZi(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "historyRongZi");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getTeamMember(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "teamMember");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getProductInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "productInfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getTouZi(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "findTzanli");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getJingPin(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "jingPin");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getShangBiao(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "tm");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getPatents(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Patents");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getCopyReg(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "CopyReg");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getIcp(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Icp");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }


    @Override
    public JSONObject getAbnormal(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Abnormal");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getPunishmentInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "PunishmentInfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getIllegalinfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Illegalinfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getOwnTax(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "OwnTax");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getNews(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("tag", dto.getUserId());
        params.put("method", "News");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getDishonest(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Dishonest");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getRiskInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        dto.setSpec(KeyConstan.URL.QIYEFENGXIAN);
        params.put("tag", dto.getUserId());
        params.put("method", "RiskInfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    //-----------------------
    public JSONObject getHumanRiskInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("humanName", dto.getHumanName());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "HumanRiskInfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getRiskDetail(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", dto.getId());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "RiskDetail");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getTaxCredit(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "TaxCredit");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getSousuoCompanyList(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("word", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("tag", dto.getUserId());
        params.put("method", "sousuo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getHolder(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Holder");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getChangeInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "ChangeInfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getInverst(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Inverst");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getBids(OpeneyesDTO dto) {//从这里开始
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Bids");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getBond(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Bond");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getPurchaseland(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Purchaseland");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getEmployment(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Employment");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getCheckInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "CheckInfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getAppbkInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "AppbkInfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getLawsuit(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Lawsuit");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getGonggao(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "CourtAnnouncement");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getZhixingInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "ZhixingInfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getVolatility(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("tag", dto.getUserId());
        params.put("method", "Volatility");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getCompanyInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("tag", dto.getUserId());
        params.put("method", "CompanyInfo");
        params.put("id", dto.getId());
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getSeniorExecutive(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "SeniorExecutive");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getHoldingCompany(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "HoldingCompany");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }


    @Override
    public JSONObject getCertificate(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Certificate");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getAnnouncement(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        params.put("tag", dto.getUserId());
        params.put("method", "Announcement");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getShareholder(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("type", dto.getPageNumber());
        params.put("tag", dto.getUserId());
        params.put("method", "Shareholder");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getIssueRelated(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("tag", dto.getUserId());
        params.put("method", "IssueRelated");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getShareStructure(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("tag", dto.getUserId());
        params.put("method", "ShareStructure");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getEquityChange(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("tag", dto.getUserId());
        params.put("method", "EquityChange");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getBonusInfo(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("tag", dto.getUserId());
        params.put("method", "BonusInfo");
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public JSONObject getAllotmen(OpeneyesDTO dto) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", dto.getCname());
        params.put("tag", dto.getUserId());
        params.put("method", "allotmen");
        params.put("pageNum", dto.getPageNumber());
        params.put("pageSize", dto.getPageSize());
        String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
        return JSONObject.parseObject(sendHttpGet);
    }

    @Override
    public void downLoad(OpeneyesDTO dto) {
        Map<String, JSONObject> data = new HashMap<>();
        //得到想要调用的接口数据
        String[] methods = dto.getMethods();
        for (String m :
                methods) {
            Map<String, Object> params = new HashMap<>();
            params.put("name", dto.getCname());
            params.put("pageNum", dto.getPageNumber());
            params.put("pageSize", dto.getPageSize());
            params.put("tag", dto.getUserId());
            params.put("method", m);
            finishParam(params);
            String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", params);
            JSONObject parse = JSONObject.parseObject(sendHttpGet);
            data.put(m, parse);
        }
        //进行导出功能
        try {
            export(data,dto.getCname(),dto.getExportType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void export(Map<String, JSONObject> data,String name,String exportType) throws Exception {
        Document document = new Document(PageSize.A4,37, 30, 50, 40);
        if(exportType.equalsIgnoreCase("pdf")) {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("d:\\new.pdf"));
        }else{
            RtfWriter2 writer = RtfWriter2.getInstance(document,new FileOutputStream("d:\\new.doc"));
        }
        document.open();
//        BaseFont bfChinese = BaseFont.createFont("SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
//        Font font1=FontFactory.getFont(FontFactory.COURIER,8, Font.BOLD, Color.BLACK);
        // 设置中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        // 标题字体风格
        Font titleFont = new Font(bfChinese, 36, Font.BOLD);
        titleFont.setColor(Color.BLUE);
        //设置空行
        Font blankFont = new Font(bfChinese, 18, Font.BOLD);
        //名称字体
        Font nameFont = new Font(bfChinese, 22, Font.BOLD);
        //目录字体
        Font directoryFont = new Font(bfChinese, 36, Font.BOLD);
        directoryFont.setColor(Color.BLUE);
        //方法名录字体
        Font methodFont = new Font(bfChinese, 14, Font.BOLD);
        methodFont.setColor(Color.BLUE);
        //具体方法中的字体
        Font methodDetailFont = new Font(bfChinese, 9, Font.BOLD);
        Font methodDetailFontV = new Font(bfChinese, 9, Font.COURIER);
        Paragraph title = new Paragraph("专业版企业信用报告",titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        Paragraph blankRow1 = new Paragraph(18f, " ", blankFont);
        document.add(blankRow1);
        document.add(blankRow1);
        document.add(blankRow1);
        //定义名称
        Paragraph companyName = new Paragraph(name,nameFont);
        companyName.setAlignment(Element.ALIGN_CENTER);
        document.add(companyName);
        //开启新的一页
        document.newPage();
        Paragraph directory = new Paragraph("目录",directoryFont);
        document.add(directory);
        document.add(blankRow1);
        document.add(blankRow1);
        //定义起始方法
        int i = 0;
        for (Map.Entry<String, JSONObject> entry :
                data.entrySet()) {
            String index = getIndex(++i);
            String methodName = getMethodName(entry.getKey());
            Paragraph method = new Paragraph(index + methodName, methodFont);
            method.setIndentationLeft(30);
            document.add(method);
        }
        document.newPage();
        //书写获取到的所有方法
        nameFont.setColor(Color.BLUE);
        methodDetailFont.setColor(Color.BLACK);
        for (Map.Entry<String,JSONObject> entry:
             data.entrySet()) {
            String k = entry.getKey();
            JSONObject v = entry.getValue().getJSONObject("result");
            String methodName = getMethodName(k);
            Paragraph method = new Paragraph(methodName,nameFont);
            document.add(method);
            document.add(blankRow1);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if(v.getLong("error_code") != null && v.getLong("error_code") == 300000){
                Paragraph noMessage = new Paragraph("暂无数据",methodDetailFont);
                document.add(noMessage);
                continue;
            }
            if(k.equalsIgnoreCase("baseinfo")){
                addKeyAndValue("公司名称",v.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("工商注册号",v.getString("regNumber"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("统一信用代码",v.getString("creditCode"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("法定代表人",v.getString("legalPersonName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("组织机构代码",v.getString("orgNumber"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("企业类型",v.getString("companyOrgType"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("所属行业",v.getString("industry"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("经营状态",v.getString("regStatus"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("注册资本",v.getString("regCapital"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("注册时间",format.format(v.getLong("estiblishTime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("注册地址",v.getString("estiblishTime"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("经营范围",v.getString("businessScope"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("登记机关",v.getString("regInstitute"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("核准日期",format.format(v.getLong("approvedTime")),methodDetailFont,methodDetailFontV,blankRow1,document);
            }else if(k.equalsIgnoreCase("staff")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                     arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("姓名",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("职位",item.getString("typeJoin"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("holder")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("金额",item.getString("amount"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("股东名",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("认缴金额",item.getJSONObject("capital").getString("amomon"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("占比",item.getJSONObject("capital").getString("percent"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("inverst")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("被投资企业名称",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("被投资法定代表人",item.getString("legalPersonName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("注册资本",item.getString("regCapital"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("投资数额",item.getString("amount"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("投资占比",item.getString("percent"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("状态",item.getString("regStatus"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    try {
                        addKeyAndValue("注册时间", format.format(item.getLong("estiblishTime")), methodDetailFont,methodDetailFontV,blankRow1,document);
                    }catch (Exception e){
                        addKeyAndValue("注册时间", "", methodDetailFont,methodDetailFontV,blankRow1,document);
                    }
                }
            }else if (k.equalsIgnoreCase("changeinfo")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("变更项目",item.getString("changeItem"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("变更前",item.getString("contentBefore"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("变更后",item.getString("contentAfter"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("变更时间",item.getString("changeTime"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if (k.equalsIgnoreCase("branch")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("行业code",item.getString("category"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("注册资金",item.getString("regCapital"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("分支名称",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("经营状态",item.getString("regStatus"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("法人",item.getString("legalPersonName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if (k.equalsIgnoreCase("findHistoryRongzi")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("融资时间",format.format(item.getLong("date")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("投资企业",item.getString("investorName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("金额",item.getString("money"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("新闻标题",item.getString("newsTitle"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("新闻url",item.getString("newsUrl"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("投资公司",item.getString("organizationName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("轮次",item.getString("round"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("投资比例",item.getString("share"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("轮次",item.getString("round"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if (k.equalsIgnoreCase("findTeamMember")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("描述",item.getString("desc"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("姓名",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("标签",item.getString("title"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("getProductInfo")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("行业",item.getString("hangye"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("产品名",item.getString("product"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("业务范围",item.getString("yewu"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("上线时间",format.format(item.getLong("setupDate")),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("findTzanli")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("产品名",item.getString("产品名"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("地区",item.getString("location"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("行业",item.getString("hangye1"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("业务范围",item.getString("yewu"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("金额",item.getString("money"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("轮次",item.getString("lunci"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("投资时间",format.format(item.getLong("tzdate")),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("findJingpin")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("公司名",item.getString("companyName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("行业",item.getString("hangye"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("竞品名",item.getString("jingpinProduct"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("地区",item.getString("location"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("产品",item.getString("product"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("轮次",item.getString("round"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("业务范围",format.format(item.getLong("yewu")),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("bids")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("标题",item.getString("title"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("摘要信息",item.getString("abs"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("采购人",item.getString("purchaser"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发布日期",format.format(item.getLong("publishTime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("更新时间",item.getString("updateTime"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("代理机构",item.getString("proxy"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("来源链接",item.getString("link"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("bond")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("债券名称",item.getString("bondName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("债券号",item.getString("bondNum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("债劵摘牌日",format.format(item.getString("bondStopTime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("债劵期限",item.getString("bondTimeLimit"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("债券类型",item.getString("bondType"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("计息方式",item.getString("calInterestType"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("信用评级机构",item.getString("creditRatingGov"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("债项评级",item.getString("debtRating"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("托管机构",item.getString("escrowAgent"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("行权类型",item.getString("exeRightType"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("票面利率(%)",item.getString("faceInterestRate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("面值",item.getString("faceValue"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("流通范围",item.getString("flowRange"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发行价格(元)",item.getString("issuedPrice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("付息频率",item.getString("payInterestHZ"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("计划发行量(亿)",item.getString("planIssuedQuantity"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("债劵到期日",item.getString("publishExpireTime"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("债劵发行日",item.getString("publishTime"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发行人",item.getString("publisherName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("实际发行量(亿)",item.getString("realIssuedQuantity"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("参考利率",item.getString("refInterestRate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("备注",item.getString("remark"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("债券起息日",format.format(item.getString("startCalInterestTime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("purchaseLand")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("行政区",item.getString("adminRegion"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("受让人",item.getString("assignee"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("创建时间",format.format(item.getString("createTime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("成交价款（万元）",item.getString("dealPrice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("约定竣工时间",format.format(item.getLong("endTime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("宗地位置",item.getString("location"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("最大容积率",item.getString("maxVolume"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("最小容积率",item.getString("minVolume"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("上级公司",item.getString("parentCompany"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("土地用途",item.getString("purpose"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("签订日期",format.format(item.getString("signedDate")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("约定动工时间",format.format(item.getString("startTime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("供应方式",item.getString("supplyWay"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("供地总面积(公顷)",item.getString("totalArea"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("employments")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("职位描述",item.getString("description"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("所在区",item.getString("district"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("学历",item.getString("education"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("招聘人数",item.getString("employerNumber"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("开始时间",format.format(item.getLong("startdate")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("结束日期",format.format(item.getLong("enddate")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("工作经验",item.getString("experience"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("薪水",item.getString("oriSalary"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("来源",item.getString("source"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("招聘职称,题目",item.getString("title"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("taxCredit")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("纳税等级",item.getString("grade"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("年份",item.getString("year"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("评价单位",item.getString("evalDepartment"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("类型",item.getString("type"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("纳税人识别号",item.getString("idNumber"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("纳税人名称",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("checkInfo")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("日期",item.getString("checkDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("检查实施机关",item.getString("checkOrg"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("类型",item.getString("checkType"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("appbkInfo")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("领域",item.getString("classes"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("产品简称",item.getString("filterName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("产品分类",item.getString("type"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("描述",item.getString("brief"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("产品名",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("certificate")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("证书编号",item.getString("certNo"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("证书类型",item.getString("certificateName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发证日期",item.getString("startDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("截止日期",item.getString("endDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("lawSuit")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("标题",item.getString("title"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("摘要",item.getString("abstracts"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("提交时间",format.format(item.getLong("submittime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("法院",item.getString("court"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("案件类型",item.getString("casetype"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("案由",item.getString("casereason"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("文书类型",item.getString("doctype"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("案件号",item.getString("caseno"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("courtAnnouncement")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("公告号",item.getString("bltnno"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("公告状态号",item.getString("bltnstate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("公告类型",item.getString("bltntype"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("公告类型名称",item.getString("bltntypename"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("法院名",item.getString("courtcode"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("处理等级",item.getString("dealgrade"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("处理等级名称",item.getString("dealgradename"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("法官",item.getString("judge"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("法官电话",item.getString("judgephone"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("原告",item.getString("party1"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("当事人",item.getString("party2"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("省份",item.getString("province"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("刊登日期",item.getString("publishdate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("刊登版面",item.getString("publishpage"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("zhixinginfo")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("被执行人名称",item.getString("pname"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("执行法院",item.getString("execCourtName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("身份证号／组织机构代码",item.getString("partyCardNum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("案号",item.getString("caseCode"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("立案时间",format.format(item.getString("caseCreateTime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("执行标的",item.getString("execMoney"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("dishonest")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("失信人名",item.getString("iname"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("案号",item.getString("casecode"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("身份证号／组织机构代码",item.getString("cardnum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("法定代表人",item.getString("businessentity"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("省份",item.getString("areaname"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("执行法院",item.getString("courtname"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("执行依据文号",item.getString("gistid"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("立案时间",item.getString("regdate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("做出执行依据单位",item.getString("gistunit"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("法律生效文书确定的义务",item.getString("duty"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("被执行人的履行情况",item.getString("performance"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发布时间",item.getString("publishdate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("tm")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("类别",item.getString("intCls"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("商标状态",item.getString("category"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("状态",item.getString("status"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("注册号",item.getString("regNo"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("申请日期",format.format(item.getLong("appDate")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("申请人",item.getString("applicantCn"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("patents")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("主分类号",item.getString("mainCatNum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("申请公布号",item.getString("applicationPublishNum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("代理机构",item.getString("agency"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("代理人",item.getString("agent"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发明人",item.getString("inventor"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("申请公布日",item.getString("applicationPublishTime"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("申请号/专利号",item.getString("patentNum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("全部分类号",item.getString("allCatNum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("专利名称",item.getString("patentName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("摘要",item.getString("abstracts"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("地址",item.getString("address"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("申请日",item.getString("applicationTime"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("专利类型",item.getString("patentType"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("申请人",item.getString("applicantName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("copyReg")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("登记日期",format.format(item.getLong("regtime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("首次发表日期",format.format(item.getLong("publishtime")),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("著作权人",item.getString("authorNationality"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("简称",item.getString("simplename"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("登记号",item.getString("regnum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("分类号",item.getString("catnum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("全称",item.getString("fullname"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("版本号",item.getString("version"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("icp")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("网站",item.getString("webSite").replace("[\"","").replace("\"",""),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("检查时间",item.getString("examineDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("公司类型",item.getString("companyType"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("网站名称",item.getString("webName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("域名",item.getString("ym"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("许可证",item.getString("liscense"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("公司名称",item.getString("companyName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("volatility")){
                addKeyAndValue("股票号",v.getString("stockcode"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("股票名",v.getString("stockname"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("股票类型",v.getString("stockType"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("更新时间",v.getString("timeshow"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("市盈率（动）",v.getString("fvaluep"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("总市值",v.getString("tvalue"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("流通市值",v.getString("flowvalue"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("市净率",v.getString("tvaluep"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("今开",v.getString("topenprice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("成交量",v.getString("tamount"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("振幅",v.getString("trange"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("最高",v.getString("thighprice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("成交额",v.getString("tamounttotal"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("换手",v.getString("tchange"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("最低",v.getString("tlowprice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("昨收",v.getString("pprice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("涨停",v.getString("tmaxprice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("跌停",v.getString("tminprice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("当前价格",v.getString("hexm_curPrice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("浮动价格",v.getString("hexm_float_price"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("浮动率",v.getString("hexm_float_rate"),methodDetailFont,methodDetailFontV,blankRow1,document);
            }else if(k.equalsIgnoreCase("companyInfo")){
                addKeyAndValue("邮编",v.getString("postalcode"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("控股股东",v.getString("controllingShareholder"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("实际控股",v.getString("actualController"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("最终控股",v.getString("finalController"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("传真",v.getString("fax"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("网址",v.getString("website"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("员工数量",v.getString("employeesNum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("主营业务",v.getString("mainBusiness"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("股票编号",v.getString("code"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("公司名",v.getString("companyName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("注册资金",v.getString("registeredCapital"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("曾用名",v.getString("usedName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("省份",v.getString("area"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("地址",v.getString("address"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("股票名",v.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("行业",v.getString("industry"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("业务",v.getString("productName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("英文名",v.getString("engName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("介绍",v.getString("introduction"),methodDetailFont,methodDetailFontV,blankRow1,document);
                addKeyAndValue("电话",v.getString("mobile"),methodDetailFont,methodDetailFontV,blankRow1,document);
            }else if(k.equalsIgnoreCase("seniorExecutive")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("职位",item.getString("position"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("性别",item.getString("sex"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("教育",item.getString("education"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("介绍",item.getString("resume"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("所属组",item.getString("managerGroup"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("本届任期",item.getString("term"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("高管名",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("年龄",item.getString("age"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("公告日期",item.getString("reportDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("薪资",item.getString("salary"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("持股数",item.getString("numberOfShares"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("holdingCompany")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("参股关系",item.getString("relationship"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("是否报表合并",item.getString("reportMerge"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("被参股公司 净利润(元)",item.getString("profit"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("公司名",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("参股比例（%）",item.getString("participationRatio"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("投资金额（万元）",item.getString("investmentAmount"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("announcement")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("发布日期",item.getString("time"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("标题",item.getString("title"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("股票号",item.getString("code"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("公司",item.getString("companyName"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("股票名",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("shareholder")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("占股本比例（%）",item.getString("proportion"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("累计占股本比例（%）",item.getString("tenPercent"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("总股数",item.getString("tenTotal"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("实际增减持（%）",item.getString("actual"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("公司名",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("持股变化 （万股）",item.getString("holdingChange"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("持股数量",item.getString("holdingNum"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("股票类型",item.getString("shareType"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("持股变化",item.getString("compareChange"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发布日期",format.format(item.getLong("publishDate")),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("issueRelated")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("预计募资",item.getString("expectedToRaise"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("历史沿革",item.getString("history"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发行数量",item.getString("issueNumber"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发行价格",item.getString("issuePrice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发行市盈率",item.getString("ipoRatio"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发行中签率",item.getString("rate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("上市日期",item.getString("listingDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("实际募资",item.getString("actualRaised"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("成立日期",item.getString("issueDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("首日开盘价",item.getString("openingPrice"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("equityChange")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("变动原因",item.getString("changeReason"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("变动后限售A股(万股)",item.getString("afterLimit"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("变动日期",item.getString("changeDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("变动后A股总股本(万股)",item.getString("afterAll"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("变动后流通A股(万股)",item.getString("afterNoLimit"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("bonusInfo")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("方案进度",item.getString("progress"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("股东大会日期",item.getString("shareholderDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("A股除权除息日",item.getString("acuxiDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("股利支付率(%)",item.getString("payment"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("A股派息日",item.getString("adividendDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("实施日期",item.getString("implementationDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("A股股权登记日",item.getString("asharesDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("分红率(%)",item.getString("dividendRate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("董事会日期",item.getString("boardDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("分红方案说明",item.getString("introduction"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else if(k.equalsIgnoreCase("allotmen")){
                JSONArray arr = v.getJSONArray("items");
                for (Object obj:
                        arr) {
                    JSONObject item = JSONObject.parseObject(obj.toString());
                    addKeyAndValue("方案进度",item.getString("progress"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("除权日",item.getString("exDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("实际配股比例",item.getString("proportion"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("实际募集资金净额",item.getString("actualRaise"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("证监会核准公告日",item.getString("pubDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("董事会公告日",item.getString("dDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("股权登记日",item.getString("registerDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("配股上市日",item.getString("issueDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("发审委公告日",item.getString("announceDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("每股配股价格",item.getString("price"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("预案配股比例上限",item.getString("proportionalLimit"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("配股号",item.getString("issueCode"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("配股简称",item.getString("name"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("配股年份",item.getString("year"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("股东大会公告日",item.getString("saDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("缴款起止日",item.getString("sDate"),methodDetailFont,methodDetailFontV,blankRow1,document);
                    addKeyAndValue("预案募资金额上限",item.getString("raiseCeiling"),methodDetailFont,methodDetailFontV,blankRow1,document);
                }
            }else{
                log.info("无对应方法");
            }

        }

        document.close();

    }

    private Map<String, Object> finishParam(Map<String, Object> params) {
        params.forEach((k, v) -> {
            if (v == null) {
                params.remove(k);
            }
        });
        return params;
    }

    private String getMethodName(String method) {
        if (method.equalsIgnoreCase("baseInfo")) {
            return "基本信息";
        } else if (method.equalsIgnoreCase("staff")) {
            return "主要人员";
        } else if (method.equalsIgnoreCase("holder")) {
            return "股东信息";
        } else if (method.equalsIgnoreCase("INVERST")) {
            return "对外投资";
        } else if (method.equalsIgnoreCase("CHANGEINFO")) {
            return "变更记录";
        } else if (method.equalsIgnoreCase("NIANBAO")) {
            return "企业年报";
        } else if (method.equalsIgnoreCase("BRANCH")) {
            return "分支机构";
        } else if (method.equalsIgnoreCase("HISTORYRONGZI")) {
            return "融资历史";
        } else if (method.equalsIgnoreCase("TEAMMEMBER")) {
            return "核心团队";
        } else if (method.equalsIgnoreCase("PRODUCTINFO")) {
            return "企业业务";
        } else if (method.equalsIgnoreCase("findTzanli")) {
            return "投资事件";
        } else if (method.equalsIgnoreCase("JINGPIN")) {
            return "竞品信息";
        } else if (method.equalsIgnoreCase("LAWSUIT")) {
            return "法律诉讼";
        } else if (method.equalsIgnoreCase("COURTANNOUNCEMENT")) {
            return "法院公告";
        } else if (method.equalsIgnoreCase("Dishonest")) {
            return "失信人";
        } else if (method.equalsIgnoreCase("ZHIXINGINFO")) {
            return "被执行人";
        } else if (method.equalsIgnoreCase("ABNORMAL")) {
            return "经营异常";
        } else if (method.equalsIgnoreCase("PunishmentInfo")) {
            return "行政处罚";
        } else if (method.equalsIgnoreCase("Illegalinfo")) {
            return "严重违法";
        } else if (method.equalsIgnoreCase("OwnTax")) {
            return "欠税公告";
        } else if (method.equalsIgnoreCase("BIDS")) {
            return "招投标";
        } else if (method.equalsIgnoreCase("BOND")) {
            return "债券信息";
        } else if (method.equalsIgnoreCase("PURCHASELAND")) {
            return "购地信息";
        } else if (method.equalsIgnoreCase("EMPLOYMENT")) {
            return "招聘";
        } else if (method.equalsIgnoreCase("TaxCredit")) {
            return "税务评级";
        } else if (method.equalsIgnoreCase("CHECKINFO")) {
            return "抽查检查";
        } else if (method.equalsIgnoreCase("APPBKINFO")) {
            return "产品信息";
        } else if (method.equalsIgnoreCase("CERTIFICATE")) {
            return "证书";
        } else if (method.equalsIgnoreCase("tm")) {
            return "商标信息";
        } else if (method.equalsIgnoreCase("PATENTS")) {
            return "专利";
        } else if (method.equalsIgnoreCase("COPYREG")) {
            return "著作权";
        } else if (method.equalsIgnoreCase("ICP")) {
            return "网站备案";
        } else if (method.equalsIgnoreCase("VOLATILITY")) {
            return "股票行情";
        } else if (method.equalsIgnoreCase("COMPANYINFO")) {
            return "企业简介（股票）";
        } else if (method.equalsIgnoreCase("HOLDINGCOMPANY")) {
            return "参股控股（股票）";
        } else if (method.equalsIgnoreCase("SENIOREXECUTIVE")) {
            return "高管信息（股票）";
        } else if (method.equalsIgnoreCase("ANNOUNCEMENT")) {
            return "上市公告（股票）";
        } else if (method.equalsIgnoreCase("SHAREHOLDER")) {
            return "十大股东（十大流通股东）（股票）";
        } else if (method.equalsIgnoreCase("ISSUERELATED")) {
            return "发行相关（股票）";
        } else if (method.equalsIgnoreCase("SHARESTRUCTURE")) {
            return "股本结构（股票）";
        } else if (method.equalsIgnoreCase("EQUITYCHANGE")) {
            return "股本变动（股票）";
        } else if (method.equalsIgnoreCase("BONUSINFO")) {
            return "分红情况（股票）";
        } else if (method.equalsIgnoreCase("ALLOTMEN")) {
            return "配股情况（股票）";
        } else if (method.equalsIgnoreCase("RiskInfo")) {
            return "企业风险";
        } else if (method.equalsIgnoreCase("RiskDetail")) {
            return "风险信息";
        } else if (method.equalsIgnoreCase("HumanRiskInfo")) {
            return "人风险";
        } else if (method.equalsIgnoreCase("news")) {
            return "公司新闻信息";
        }else{
            return "";
        }
    }

    private String getIndex(int i){
        if(i == 1){
            return "一、";
        }else if(i==2){
            return "二、";
        }else if(i==3){
            return "三、";
        }else if(i==4){
            return "四、";
        }else if(i==5){
            return "五、";
        }else if(i==6){
            return "六、";
        }else if(i==7){
            return "七、";
        }else if(i==8){
            return "八、";
        }else if(i==9){
            return "九、";
        }else if(i==10){
            return "十、";
        }else if(i==11){
            return "十一、";
        }else if(i==12){
            return "十二、";
        }else if(i==13){
            return "十三、";
        }else if(i==14){
            return "十四、";
        }else if(i==15){
            return "十五、";
        }else if(i==16){
            return "十六、";
        }else if(i==17){
            return "十七、";
        }else if(i==18){
            return "十八、";
        }else if(i==19){
            return "十九、";
        }else if(i==20){
            return "二十、";
        }else if(i==21){
            return "二十一、";
        }else if(i==22){
            return "二十二、";
        }else if(i==23){
            return "二十三、";
        }else if(i==24){
            return "二十四、";
        }else if(i==25){
            return "二十五、";
        }else if(i==26){
            return "二十六、";
        }else if(i==27){
            return "二十七、";
        }else if(i==28){
            return "二十八、";
        }else if(i==29){
            return "二十九、";
        }else{
            return "";
        }
    }
    private void addKeyAndValue(String k,String v,Font kfont,Font vfont,Paragraph blankRow1,Document document){
        Chunk nameK = new Chunk(k+":", kfont);
        Chunk nameV = new Chunk(v == null?"":v, vfont);
        Paragraph nameP = new Paragraph();
        nameP.add(nameK);
        nameP.add(nameV);
        try {
            document.add(nameP);
            document.add(blankRow1);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws Exception {
        Table table = new Table(5,5);
        Cell cell = new Cell();
//        cell.
//        table.add

    }
}
