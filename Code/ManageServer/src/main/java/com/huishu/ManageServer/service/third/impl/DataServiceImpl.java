/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataServiceImpl
 * Author:   12080
 * Date:     2018/5/17 17:57
 * Description: aa
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.huishu.ManageServer.service.third.impl;

import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.entity.dbThird.KeyWordRelatedEntity;
import com.huishu.ManageServer.entity.dbThird.KeywordInfoEntity;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;
import com.huishu.ManageServer.repository.third.*;
import com.huishu.ManageServer.service.third.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
@Service
public class DataServiceImpl implements DataService {
    @Resource
    private ThesaurusRepository rep; //词汇
    @Resource
    private KeyWordRelatedRepository krp;//词库之间关联关系
    @Resource
    private AttributeRepository arp;//属性
    @Resource
    private KeywordTypeRepository ktp;//词汇类型
    @Resource
    private KeyWordInfoRepository kip;//编号以及词性关系
    @Resource
    private KeyWordRelatetionRepository kwp;//关系词

    @Override
    public void addDataInfo(String value) {
        String[] split2 = value.split("---");//0,代表产业词；1，代表公司名录
        String industry = split2[0];
        String company = split2[1];
        //首先验证产业词是否存在
        ThesaurusEntity one =  rep.findByKeyword(industry);
        if(one == null){
            one = new ThesaurusEntity();
            one.setKeyword(industry);
            one = rep.save(one);//保存词
        }
        //验证公司是否录入
        ThesaurusEntity two = rep.findByKeyword(company);
        if(two == null ){
            two = new ThesaurusEntity();
            two.setKeyword(company);
            two = rep.save(two);
        }
        Long typeId = (long) 4;
        KeywordInfoEntity ent = kip.findByWordId(two.getId());
        if(ent == null){
            ent = new KeywordInfoEntity();
            ent.setInsertTime(new Date());
            ent.setWordId(two.getId());
            Long count = kip.getCount(typeId);
            String num = StringUtil.LC_FIRST[(typeId.intValue()-1)];
            String _wordNumber = num+(count+1);
            ent.setWordNumber(_wordNumber);
            kip.save(ent);
        }


        //关系表
        KeyWordRelatedEntity tity =	krp.findByWordIdAndRelateWordId(one.getId(),two.getId());
        if(tity ==null){
            tity = new KeyWordRelatedEntity();
            tity.setRelateId((long) 2);
            tity.setRelateWordId(two.getId());
            tity.setWordId(one.getId());
            krp.save(tity);
        }

      }
    }

