package com.huishu.ait.es.entity.dto;

import static com.huishu.ait.common.util.UtilsHelper.getValueByFieldName;
import static org.apache.commons.lang.StringUtils.isEmpty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.huishu.ait.common.conf.ConfConstant;

/**
 * <pre>
 * 抽象的数据传输对象
 * </pre>
 * 
 * 提供公用的builderQuery方法
 * 
 * @author yuwei
 */
public abstract class AbstractDTO {

	private static Logger log = LoggerFactory.getLogger(AbstractDTO.class);

	/**
	 * 特殊的属性名
	 */
	private static final List<String> KEYWORDS = Arrays.asList("startDate", "endDate", "keyword", "keywords", "orders",
			"pageNumber", "pageSize");

	private List<String> fieldNames;

	public AbstractDTO() {
		fieldNames = new ArrayList<>();
		Field[] fields = getClass().getDeclaredFields();
		for (Field f : fields) {
			fieldNames.add(f.getName());
		}
	}

	public BoolQueryBuilder builderQuery() {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		for (String name : fieldNames) {
			if (KEYWORDS.contains(name)) {
				continue;
			}
			Object value = getValueByFieldName(this, name);

			if (value != null) {
				if (value instanceof String) {
					queryBuilder.must(QueryBuilders.termQuery(name, (String) value));
				} else if (value instanceof Boolean) {
					queryBuilder.must(QueryBuilders.termQuery(name, (Boolean) value));
				} else if (value instanceof List) {
					@SuppressWarnings({ "rawtypes", "unchecked" })
					List<String> values = (List) value;
					if (values.isEmpty()) {
						continue;
					}
					BoolQueryBuilder bool = QueryBuilders.boolQuery();
					for (String v : values) {
						bool.should(QueryBuilders.termQuery(name, v));
					}
					queryBuilder.must(bool);
				}
			}
		}

		if (fieldNames.contains("startDate") && fieldNames.contains("endDate")) {
			String startDate = (String) getValueByFieldName(this, "startDate");
			String endDate = (String) getValueByFieldName(this, "endDate");

			if (!isEmpty(startDate) && !isEmpty(endDate)) {
				if (startDate.length() > 10) {
					QueryBuilder range = QueryBuilders.rangeQuery("publishDateTime") // yyyy-MM-dd
																						// HH:mm:ss
							.gte(startDate).lte(endDate);
					queryBuilder.must(range);
				} else {
					QueryBuilder range = QueryBuilders.rangeQuery("publishDate") // yyyy-MM-dd
							.gte(startDate).lte(endDate);
					queryBuilder.must(range);
				}
			}
		}

		if (fieldNames.contains("keyword")) {
			String keyword = (String) getValueByFieldName(this, "keyword");
			if (!isEmpty(keyword)) {
				queryBuilder.must(QueryBuilders.wildcardQuery("content", "*" + keyword + "*"));
			}
		}

		if (fieldNames.contains("keywords")) {
			@SuppressWarnings("unchecked")
			List<String> keywords = (List<String>) getValueByFieldName(this, "keywords");
			if (keywords != null && !keywords.isEmpty()) {
				BoolQueryBuilder bool = QueryBuilders.boolQuery();
				for (String keyword : keywords) {
					bool.should(QueryBuilders.wildcardQuery("content", "*" + keyword + "*"));
				}
				queryBuilder.must(bool);
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("builderQuery: \n {}", queryBuilder);
		}

		return queryBuilder;
	}

	/**
	 * 构建 PageRequest
	 * 
	 * @return PageRequest
	 */
	public PageRequest builderPageRequest() {

		Integer pageNumber = (Integer) getValueByFieldName(this, "pageNumber");
		Integer pageSize = (Integer) getValueByFieldName(this, "pageSize");
		if (pageNumber == null) {
			pageNumber = ConfConstant.MIN_PAGE_NUMBER;
		} else if (pageNumber > ConfConstant.MAX_PAGE_NUMBER) {
			pageNumber = ConfConstant.MAX_PAGE_NUMBER;
		} else if (pageNumber < ConfConstant.MIN_PAGE_NUMBER) {
			pageNumber = ConfConstant.MIN_PAGE_NUMBER;
		}

		if (pageSize == null) {
			pageSize = ConfConstant.DEFAULT_PAGE_SIZE;
		} else if (pageSize > ConfConstant.MAX_PAGE_SIZE) {
			pageSize = ConfConstant.MAX_PAGE_SIZE;
		} else if (pageSize < ConfConstant.MIN_PAGE_SIZE) {
			pageSize = ConfConstant.MIN_PAGE_SIZE;
		}

		Order[] o = builderOrders();
		if (o == null) {
			return new PageRequest(pageNumber, pageSize);
		}
		return new PageRequest(pageNumber, pageSize, new Sort(o));
	}

	/**
	 * 构建 Order
	 * 
	 * @return Order[]
	 */
	private Order[] builderOrders() {
		if (!fieldNames.contains("orders")) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<MyOrder> orders = (List<MyOrder>) getValueByFieldName(this, "orders");
		if (orders == null || orders.isEmpty()) {
			return null;
		}
		Order o[] = new Order[orders.size()];
		for (int i = 0; i < orders.size(); i++) {
			MyOrder myOrder = orders.get(i);
			if (isEmpty(myOrder.getDirection()) || isEmpty(myOrder.getProperty())) {
				continue;
			}
			if ("ASC".equals(myOrder.getDirection())) {
				o[i] = new Order(Direction.ASC, myOrder.getProperty());
			} else if ("DESC".equals(myOrder.getDirection())) {
				o[i] = new Order(Direction.DESC, myOrder.getProperty());
			} else {
				log.warn("myOrder 参数不合法 , {} ", myOrder);
				return null;
			}
		}
		return o;
	}

	/**
	 * 自定义Order对象用于接收数据
	 */
	public static class MyOrder {

		private String direction;

		private String property;

		public MyOrder() {
		}

		public MyOrder(String direction, String property) {
			super();
			this.direction = direction;
			this.property = property;
		}

		public String getDirection() {
			return direction;
		}

		public void setDirection(String direction) {
			this.direction = direction;
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

		@Override
		public String toString() {
			return "MyOrder [direction=" + direction + ", property=" + property + "]";
		}
	}

	public List<String> getFieldNames() {
		return fieldNames;
	}

}
