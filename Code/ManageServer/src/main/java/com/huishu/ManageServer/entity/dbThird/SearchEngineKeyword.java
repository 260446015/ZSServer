package com.huishu.ManageServer.entity.dbThird;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yindq
 * @since 2018-04-03
 */
@Entity
@Table(name = "t_search_engine_keyword")
public class SearchEngineKeyword implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "Create_Time")
	private String createTime;
	@Column(name = "Disabled")
	private Long disabled;
	@Column(name = "Exec_Count")
	private Long execCount;
	@Column(name = "KeyWord")
	private String keyWord;
	@Column(name = "Result")
	private Long result;
	@Column(name = "ResultLastDay")
	private Long resultLastDay;
	@Column(name = "ResultLastDayRel")
	private Long resultLastDayRel;
	@Column(name = "ResultRel")
	private Long resultRel;
	@Column(name = "Spider_Begin_Time")
	private String spiderBeginTime;
	@Column(name = "Spider_Code")
	private String spiderCode;
	@Column(name = "Spider_End_Time")
	private String spiderEndTime;
	@Column(name = "Spider_Last_Work_Time")
	private String spiderLastWorkTime;
	@Column(name = "Tag")
	private String tag;
	@Column(name = "Spider_Result")
	private Long spiderResult;
	@Column(name = "spider_State")
	private Long spiderState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getDisabled() {
		return disabled;
	}

	public void setDisabled(Long disabled) {
		this.disabled = disabled;
	}

	public Long getExecCount() {
		return execCount;
	}

	public void setExecCount(Long execCount) {
		this.execCount = execCount;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public Long getResultLastDay() {
		return resultLastDay;
	}

	public void setResultLastDay(Long resultLastDay) {
		this.resultLastDay = resultLastDay;
	}

	public Long getResultLastDayRel() {
		return resultLastDayRel;
	}

	public void setResultLastDayRel(Long resultLastDayRel) {
		this.resultLastDayRel = resultLastDayRel;
	}

	public Long getResultRel() {
		return resultRel;
	}

	public void setResultRel(Long resultRel) {
		this.resultRel = resultRel;
	}

	public String getSpiderBeginTime() {
		return spiderBeginTime;
	}

	public void setSpiderBeginTime(String spiderBeginTime) {
		this.spiderBeginTime = spiderBeginTime;
	}

	public String getSpiderCode() {
		return spiderCode;
	}

	public void setSpiderCode(String spiderCode) {
		this.spiderCode = spiderCode;
	}

	public String getSpiderEndTime() {
		return spiderEndTime;
	}

	public void setSpiderEndTime(String spiderEndTime) {
		this.spiderEndTime = spiderEndTime;
	}

	public String getSpiderLastWorkTime() {
		return spiderLastWorkTime;
	}

	public void setSpiderLastWorkTime(String spiderLastWorkTime) {
		this.spiderLastWorkTime = spiderLastWorkTime;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getSpiderResult() {
		return spiderResult;
	}

	public void setSpiderResult(Long spiderResult) {
		this.spiderResult = spiderResult;
	}

	public Long getSpiderState() {
		return spiderState;
	}

	public void setSpiderState(Long spiderState) {
		this.spiderState = spiderState;
	}
}
