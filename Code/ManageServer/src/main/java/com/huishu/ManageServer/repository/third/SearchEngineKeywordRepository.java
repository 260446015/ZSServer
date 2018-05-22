package com.huishu.ManageServer.repository.third;

import com.huishu.ManageServer.config.TargetDataSource;
import com.huishu.ManageServer.entity.dbThird.SearchEngineKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yindq
 * @since 2018-04-03
 */
@Repository
@TargetDataSource(name="third")
public interface SearchEngineKeywordRepository extends JpaRepository<SearchEngineKeyword,Long> {
	/**
	 * 获得所有的关键词
	 * @return
	 */
	@Query(value="SELECT KeyWord from t_search_engine_keyword",nativeQuery=true)
	List<String> findAllKeyWork();

	/**
	 * 根据关键词搜索数据
	 * @param keyWord
	 * @return
	 */
	@Query(value="SELECT KeyWord from t_search_engine_keyword WHERE KeyWord like ?",nativeQuery=true)
	List<String> findByKeyWordLike(String keyWord);

	/**
	 * 获取最大的ID
	 * @return
	 */
	@Query(value="SELECT max(id) from t_search_engine_keyword",nativeQuery=true)
	Integer findMaxId();

	/**
	 * 添加关键词
	 * @param createTime
	 * @param keyWord
	 */
	@Modifying
	@Query(value="insert into t_search_engine_keyword(id,Create_Time,Disabled,Exec_Count,KeyWord,Result," +
			"ResultLastDay,ResultRel,ResultLastDayRel,Spider_Begin_Time,Spider_Code,Spider_End_Time," +
			"Spider_Last_Work_Time,Tag,Spider_Result,spider_State) values " +
			"(?,?,0,0,?,0,0,0,0,null,null,null,null,null,0,null)",nativeQuery=true)
	void savaKeyWord(Integer id,String createTime,String keyWord);
}