package com.huishu.ZSServer.service.company.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huishu.ZSServer.entity.dto.BaseInfoCustom;
import com.huishu.ZSServer.entity.dto.OpeneyesDTO;
import com.huishu.ZSServer.service.openeyes.OpeneyesService;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.common.conf.KeyConstan;
import com.huishu.ZSServer.common.conf.MsgConstant;
import com.huishu.ZSServer.common.util.HttpUtils;
import com.huishu.ZSServer.entity.dto.IndusCompanyDTO;
import com.huishu.ZSServer.repository.company.IndusCompanyDTORepository;
import com.huishu.ZSServer.service.AbstractService;
import com.huishu.ZSServer.service.company.IndusCompanyDTOService;

/**
 * @author hhy
 * @date 2017年11月9日
 * @Parem
 * @return 智能招商
 */
@Service
public class IndusCompanyDTOServiceImpl extends AbstractService implements IndusCompanyDTOService {

    private static final Logger LOGGER = Logger.getLogger(IndusCompanyDTOServiceImpl.class);

    @Autowired
    private IndusCompanyDTORepository repository;

    @Autowired
    private OpeneyesService openeyesService;

    @Override
    public BaseInfoCustom getCompanyInfo(String companyName,Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
//		IndusCompany com = repository.findByCompanyName(companyName);
        IndusCompanyDTO com = repository.findByCompanyName(companyName);
        if (com == null) {
            LOGGER.debug(MsgConstant.SYSTEM_ERROR);
            return null;
        }
        OpeneyesDTO dto = new OpeneyesDTO();
        dto.setUserId(userId);
        dto.setCname(com.getCompany());
        JSONObject baseInfo1 = openeyesService.getBaseInfo(dto);
        BaseInfoCustom baseInfo = JSONObject.parseObject(baseInfo1.toJSONString(),BaseInfoCustom.class);
        if (baseInfo != null) {
            com.setIndustry(baseInfo.getIndustry());
            return baseInfo;
        } else {
            map.put("name", com.getCompany());
            map.put("method", "baseInfo");
            map.put("tag", KeyConstan.From.CUSTOM);
            String sendHttpGet = HttpUtils.sendHttpGet("http://58.16.181.24:10002/openeyes/search.json", map);
            JSONObject obj = JSONObject.parseObject(sendHttpGet);
            JSONObject object = obj.getJSONObject("result");
            BaseInfoCustom info = object.parseObject(object.toJSONString(), BaseInfoCustom.class);
            com.setIndustry(info.getIndustry());
            repository.save(com);
            return info;
        }
    }

    @Override
//	public List<IndusCompany> listCompany() {
    public List<IndusCompanyDTO> listCompany() {
        // 获取总数
        int count = repository.getCount();

        int i = (int) (Math.random() * count);
        while (i > (count - 10)) {
            i = (int) (Math.random() * count + 1);
        }
        List<IndusCompanyDTO> list = repository.getCompanyInfo(i);
//		List<IndusCompany> list =  repository.getCompanyInfo(i);
        return list;
    }

    @Override
    public String uploadImage(String imageBase64) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("src_img ", imageBase64));
        JSONObject object = HttpUtils.sendPost(KeyConstan.DISTINGUISH, params);
        String imgMsg = object.getString("data");
        String[] split = imgMsg.split("\r\n");
        String company = "";
        for (String string : split) {
            if (string.indexOf("公司") != -1 || string.indexOf("办司") != -1 || string.indexOf("有限") != -1 || string.indexOf("集团") != -1) {
                String replaceAll = string.replaceAll("[\\p{Punct}\\p{Space}A-Za-z0-9]+", "");
                company = replaceAll.substring(0, replaceAll.length() - 2);
                break;
            }
        }
        return company;
    }

    /**
     * 根据别名查看具体的公司信息
     */
    @Override
//	public IndusCompany findCompanyInfoByCompanyName(String companyName) {
    public IndusCompanyDTO findCompanyInfoByCompanyName(String companyName) {

        return repository.findByCompanyName(companyName);
    }
}
