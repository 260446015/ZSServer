package com.huishu.ait.app.conf;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务
 *
 * @author yindq
 * @date 2018/1/19
 */
@Configuration
public class QuartzConfiguration {

	/**
	 * 配置高峰论坛时间校验定时任务
	 * @return
	 */
	@Bean(name = "jobDetail")
	public MethodInvokingJobDetailFactoryBean detailFactoryBean() {
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		// 是否并发执行
		jobDetail.setConcurrent(false);
		jobDetail.setName("checkReleaseTime");
		jobDetail.setGroup("group1");
		jobDetail.setTargetObject(getTaskConfiguration());
		//类中方法名
		jobDetail.setTargetMethod("checkReleaseTime");
		return jobDetail;
	}

	/**
	 * 配置高峰论坛时间校验定时任务的触发器
	 * @return
	 */
	@Bean(name = "jobTrigger")
	 public CronTriggerFactoryBean cronJobTrigger() {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setJobDetail(detailFactoryBean().getObject());
		//每天0点运行
		trigger.setCronExpression("0 0 0 * * ? *");
		trigger.setName("trigger");
		return trigger;
	}

	/**
	 * 定义quartz调度工厂
	 * @param jobTrigger
	 * @return
	 */
	@Bean(name = "scheduler")
	public SchedulerFactoryBean schedulerFactory(Trigger jobTrigger) {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		bean.setOverwriteExistingJobs(true);
		bean.setStartupDelay(1);
		bean.setTriggers(jobTrigger);
		return bean;
	}

	/**
	 * 定时任务类
	 * @return
	 */
	@Bean(name = "taskJob")
	public TaskConfiguration getTaskConfiguration() {
		return new TaskConfiguration();
	}
}
