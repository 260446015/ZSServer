package com.huishu.ManageServer.entity.dto.dbThird;

import java.util.List;

/**
 * @author hhy
 * @date 2018年4月12日
 * @Parem
 * @return 
 * 
 */
public class RelatedDTO {
		// 关系词 
		private String keyword;
		//关键词id
		private List<Long> msg;
		//关联词id
		private Long relatedId;
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public List<Long> getMsg() {
			return msg;
		}
		public void setMsg(List<Long> msg) {
			this.msg = msg;
		}
		public Long getRelatedId() {
			return relatedId;
		}
		public void setRelatedId(Long relatedId) {
			this.relatedId = relatedId;
		}
		
		
}
