package com.huishu.ait.repository.skyeye;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huishu.ait.entity.SearchTrack;

public interface SearchTrackRepository extends PagingAndSortingRepository<SearchTrack, Integer> {

	Page<SearchTrack> findByUsername(String username, Pageable pageRequest);

}
