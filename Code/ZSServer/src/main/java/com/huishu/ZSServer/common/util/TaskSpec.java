package com.huishu.ZSServer.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.huishu.ZSServer.entity.Company;

/**
 * Specification工具类
 * 
 * @author yindq
 * @date 2017年10月30日
 */
public class TaskSpec {
	/**
	 * 根据地域，产业，融资轮数查询
	 * @param area
	 * @param industry
	 * @param invest
	 * @return
	 */
	public static Specification<Company> getCompanySpec(String area, String industry, String invest) {
		return new Specification<Company>() {
			@Override
			public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (!"全部".equals(area)) {
					predicates.add(cb.equal(root.<String> get("area"), area));
				}
				if (!"全部".equals(industry)) {
					predicates.add(cb.like(root.<String> get("industry"), industry));
				}
				if (!"全部".equals(invest)) {
					predicates.add(cb.like(root.<String> get("invest"), invest));
				}
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
			}
		};
	}
	
}