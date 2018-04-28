package com.huishu.ZSServer.service.atlas.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.ZSServer.entity.atlas.*;
import com.huishu.ZSServer.repository.atlas.*;
import com.huishu.ZSServer.security.Digests;
import com.huishu.ZSServer.security.Encodes;
import com.huishu.ZSServer.service.atlas.AtlasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class AtlasServiceImpl implements AtlasService {

    @Autowired
    private ThesaurusRepository thesaurusRepository;
    @Autowired
    private KeyWordRelatedRepository keyWordRelatedRepository;
    @Autowired
    private KeyWordRelatetionRepository keyWordRelatetionRepository;
    @Autowired
    private KeywordTypeRepository keywordTypeRepository;
    @Autowired
    private KeyWordInfoRepository keyWordInfoRepository;
    @Autowired
    private ExecutorService executorService;
    private static final int MAX_ALTAS_COUNT = 25;

    @Override
    public JSONObject getAtlasAndResponse(String subject) {
        //一、获取数据库中百词百科数据和词性关系
        Map<String, String> relationMap = new HashMap<>();
        long startTime = System.currentTimeMillis();
        JSONArray encyclopedia = getEncyclopediaFromDataBase(subject, relationMap);
        long endTime = System.currentTimeMillis();
        System.out.println("查询词性关系耗时间：" + (endTime - startTime));
        //二、获取nodes数据
        Set<String> checkSource = new HashSet<>();
        JSONArray d3NodesJsonData = getD3NodesJsonData(encyclopedia, relationMap, checkSource);
        JSONArray d3RelationShipsData = getD3RelationShipsData(encyclopedia);
        JSONObject successData = new JSONObject();
        JSONObject successJson = new JSONObject();
        JSONArray resultJson = new JSONArray();
        JSONObject resultData = new JSONObject();
        resultData.put("columns", new String[]{"nodes", "rels"});
        JSONArray dataArray = new JSONArray();
        JSONObject data = new JSONObject();
        JSONObject graph = new JSONObject();
        graph.put("nodes", d3NodesJsonData);
        graph.put("relationships", d3RelationShipsData);
        data.put("graph", graph);
        dataArray.add(data);
        resultData.put("data", dataArray);
        resultJson.add(resultData);
        successJson.put("results", resultJson);
        successData.put("success", successJson);
        System.out.println(successData);
        return successData;
    }

    private JSONArray getD3RelationShipsData(JSONArray encyclopedia) {
        JSONArray relationships = new JSONArray();
        for (int i = 1;i<=encyclopedia.size();i++) {
            JSONObject jsonObject = JSONObject.parseObject(encyclopedia.get(i-1).toString());
//            try {
//                if(jsonObject.getString("subject").equals(jsonObject.getJSONObject("properties").getString("name"))){
//                    continue;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            JSONObject relation = new JSONObject();
            relation.put("type", exchange(jsonObject.getString("relation")));
            relation.put("id", i);
            relation.put("startNode", jsonObject.getString("subject").hashCode());
            JSONObject propertiesObj = new JSONObject();
            propertiesObj.put("role", jsonObject.getString("relation"));
            propertiesObj.put("stockPercent", 100);
            relation.put("properties", propertiesObj);
            relation.put("endNode", jsonObject.getString("object").hashCode());
            if (jsonObject.getString("relation").length() >= 2)
                relationships.add(relation);
        }
        return relationships;
    }

    private JSONArray getD3NodesJsonData(JSONArray encyclopedia, Map<String, String> relationMap, Set<String> checkSource) {
        JSONArray links = new JSONArray();
        for (Object obj : encyclopedia) {
            JSONObject jsonObject = JSONObject.parseObject(obj.toString());
            if (checkSource.add(jsonObject.getString("subject"))) {
                JSONObject link = new JSONObject();
                link.put("id", jsonObject.getString("subject").hashCode());
                String lables = relationMap.get(jsonObject.getString("object"));
                link.put("labels", exchangeLables(lables));
                JSONObject properties = new JSONObject();
                properties.put("name", jsonObject.getString("subject"));
                properties.put("keyNo", getGeneratedId(jsonObject.getString("subject")));
                link.put("properties", properties);
                link.put("subject", jsonObject.getString("subject"));
                link.put("relation", jsonObject.getString("relation"));
                links.add(link);
            }
            if (!checkSource.add(jsonObject.getString("object"))) {
                continue;
            }
            JSONObject link = new JSONObject();
            link.put("id", jsonObject.getString("object").hashCode());
            link.put("labels", exchangeLables(relationMap.get(jsonObject.getString("object"))));
            JSONObject properties = new JSONObject();
            properties.put("name", jsonObject.getString("object"));
            properties.put("keyNo", getGeneratedId(jsonObject.getString("object")));
            link.put("subject", jsonObject.getString("subject"));
            properties.put("role", jsonObject.getString("relation"));
            link.put("properties", properties);
            links.add(link);
        }
        return links;
    }

    @Override
    public JSONArray getEncyclopediaFromDataBase(String subject, Map<String, String> relationMap) {
        //查询t_word表中的数据
        ThesaurusEntity thesaurusEntity = thesaurusRepository.findByKeyword(subject);
        if (thesaurusEntity == null) {
            throw new RuntimeException("thesaurusEntity is not fond");
        }
        Long id = thesaurusEntity.getId();
        //查询t_word_related表中的数据
        Set<KeyWordRelatedEntity> byWordId = keyWordRelatedRepository.getByWordId(id);
        JSONArray encyclopedia = new JSONArray();
        try {
            Random random = new Random();
            Set<KeyWordRelatedEntity> collect;
            Integer countDown = 0;
            if(byWordId.size() > MAX_ALTAS_COUNT){
                collect = byWordId.stream().skip(random.nextInt(byWordId.size() - MAX_ALTAS_COUNT)).limit(MAX_ALTAS_COUNT). collect(Collectors.toSet());
                countDown = MAX_ALTAS_COUNT;
            }else{
                collect = byWordId;
                countDown = byWordId.size();
            }
            CountDownLatch countDownLatch = new CountDownLatch(countDown);
            for (KeyWordRelatedEntity relate : collect) {

                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject relateObj = new JSONObject();
                        //查询到关系对象
                        ThesaurusEntity relationEntity = thesaurusRepository.findOne(relate.getRelateWordId());
                        //获取关系词！！！
                        Long relateWordId = relate.getRelateId();
                        RelatedWordEntity relatedWordEntity = keyWordRelatetionRepository.findOne(relateWordId);
                        String relatetion = relatedWordEntity.getRelatetion();
                        //创建词性关系
                        KeywordInfoEntity infoEntity = keyWordInfoRepository.findByWordId(relationEntity.getId());
                        if(infoEntity != null){
                            KeywordTypeEntity typeEntity = keywordTypeRepository.findOne(infoEntity.getTypeId());
                            relationMap.put(relationEntity.getKeyword(), typeEntity.getTypeWord());
                        }else{
                            relationMap.put(relationEntity.getKeyword(), "其他");
                        }
                        relateObj.put("subject", subject);
                        relateObj.put("object", relationEntity.getKeyword());
                        relateObj.put("relation", relatetion);
                        encyclopedia.add(relateObj);
                        countDownLatch.countDown();
                    }
                });
            }
            try {
                countDownLatch.await();
            }  catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encyclopedia;
    }


    @Override
    public JSONArray getAtlasCompany(String name) {
        ThesaurusEntity thesaurusEntity = thesaurusRepository.findByKeyword(name);
        if (thesaurusEntity == null) {
            return null;
        }
        Long thesaurusEntityId = thesaurusEntity.getId();
        List<KeyWordRelatedEntity> byWordIdAndRelateId = keyWordRelatedRepository.findByWordIdAndRelateId(thesaurusEntityId, 2L);
        if (byWordIdAndRelateId.size() == 0) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        Set<Long> idSet = new HashSet<>();
        Set<Long> newIdSet = new HashSet<>();
        byWordIdAndRelateId.forEach(action -> idSet.add(action.getRelateWordId()));
        if (idSet.size() == 0) {
            return null;
        } else {
            if (idSet.size() >= 10) {
                Random random = new Random();
                newIdSet = idSet.stream().skip(random.nextInt(idSet.size() - 10)).limit(10).collect(Collectors.toSet());
                thesaurusRepository.findAll(newIdSet).forEach(action -> jsonArray.add(action.getKeyword()));
            } else {
                thesaurusRepository.findAll(idSet).forEach(action -> jsonArray.add(action.getKeyword()));
            }
        }
        return jsonArray;
    }

    private String exchange(String str) {
        if (str.equals("包含")) {
            return "INCLUDE";
        } else if ("支撑".equals(str)) {
            return "SUPPORT";
        } else if ("载体".equals(str)) {
            return "VECTOR";
        } else if ("应用于".equals(str)) {
            return "APPLY";
        } else if ("相关".equals(str)) {
            return "RELATEUDE";
        } else {
            return "OTHER";
        }
    }

    private String[] exchangeLables(String str) {

        if ("描述性".equals(str)) {
            return new String[]{"descriptive"};
        } else if ("业务性".equals(str)) {
            return new String[]{"limited"};
        } else {
            return new String[]{"rest"};
        }
    }

    private String getGeneratedId(Object info) {
        byte[] hashPassword = Digests.sha1(info.toString().getBytes(), null, Encodes.HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

    @Override
    public String getKeyNo(String name) {
        return getGeneratedId(name);
    }
}
