package com.huishu.ait.common.conf;


/**
 * 数据库常量类
 * <br/>
 * 该类存放数据库字段值映射常量
 */
public class DBConstant {

    /**
     * es相关配置
     */
    public static class EsConfig {
        public static final String INDEX = "aitserver_2017-08-15";
        public static final String INDEX1 = "aitserver_business";
        public static final String TYPE = "aitinfo";
    }


    /**
     * es 情感
     */
    public static class Emotion {
        /**
         * 正面
         */
        public static final String POSITIVE = "positive";

        /**
         * 负面
         */
        public static final String NEGATIVE = "negative";

       
    }

   
   

    /**
     * 会员级别
     */
    public static class UserLevel {
        public static final String TRIAL = "试用辖区";
        public static final String A = "A级会员";
        public static final String B = "B级会员";
        public static final String C = "C级会员";
    }

    


    
   
 

}
