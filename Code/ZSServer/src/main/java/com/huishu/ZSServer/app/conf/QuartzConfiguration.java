package com.huishu.ZSServer.app.conf;

import org.quartz.CronTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 对接与天眼查同步数据
 * 
 * @author yindq
 * @date 2017年11月2日
 */
@Configuration
public class QuartzConfiguration {

	/**
	 * 配置融资定时任务   
	 * @return
	 */
    @Bean(name = "jobDetail")  
    public MethodInvokingJobDetailFactoryBean detailFactoryBean() {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();  
        // 是否并发执行 
        jobDetail.setConcurrent(false);  
        jobDetail.setName("getCompanyFinancing");
        jobDetail.setGroup("group1");
        jobDetail.setTargetObject(new TaskConfiguration());  
        //类中方法名 
        jobDetail.setTargetMethod("getCompanyFinancing");  
        return jobDetail;  
    }  
    
    /**
	 * 配置年报定时任务   
	 * @return
	 */
    @Bean(name = "jobDetail2")  
    public MethodInvokingJobDetailFactoryBean detailFactoryBean2() {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();  
        // 是否并发执行 
        jobDetail.setConcurrent(false);  
        jobDetail.setName("getCompanyAnnals");
        jobDetail.setGroup("group2");
        jobDetail.setTargetObject(new TaskConfiguration());  
        //类中方法名 
        jobDetail.setTargetMethod("getCompanyAnnals");  
        return jobDetail;  
    }  
      
    /**
     * 配置融资定时任务的触发器 
     * @return
     */
    @Bean(name = "jobTrigger")  
    public CronTriggerFactoryBean cronJobTrigger() {  
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();  
        tigger.setJobDetail(detailFactoryBean().getObject()); 
        //每天0点运行
        tigger.setCronExpression("0 0 0 * * ? *");
        tigger.setName("trigger1");
        return tigger;  
  
    } 
    
    /**
     * 配置年报定时任务的触发器 
     * @return
     */
    @Bean(name = "jobTrigger2")  
    public CronTriggerFactoryBean cronJobTrigger2() {  
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();  
        tigger.setJobDetail(detailFactoryBean2().getObject()); 
        //每年运行一次
        tigger.setCronExpression("0 0 0 1 1 ? ");
        tigger.setName("trigger2");
        return tigger;  
  
    } 
  
    /**
     * 定义quartz调度工厂   
     * @param cronJobTrigger
     * @return
     */
    @Bean(name = "scheduler")  
    public SchedulerFactoryBean schedulerFactory(CronTrigger jobTrigger,CronTrigger jobTrigger2) {  
    	CronTrigger[] job=new CronTrigger[]{jobTrigger,jobTrigger2};
        SchedulerFactoryBean bean = new SchedulerFactoryBean();  
        bean.setOverwriteExistingJobs(true);  
        bean.setStartupDelay(1);  
        bean.setTriggers(job);
        return bean;  
    }  
}
