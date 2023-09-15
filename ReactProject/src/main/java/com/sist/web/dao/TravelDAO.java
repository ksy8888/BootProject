package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.TravelEntity;
import java.util.*;

@Repository
public interface TravelDAO extends JpaRepository<TravelEntity, Integer> {
	
	@Query(value="SELECT * FROM travel_detail "
			+ "WHERE title LIKE CONCAT('%',:title,'%') "
			+ "LIMIT :start,12",nativeQuery=true)
	public List<TravelEntity> travelListData(@Param("title") String title, @Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM travel_detail WHERE title LIKE CONCAT('%',:title,'%')",nativeQuery=true)
	public int travelTotalPage(String title);
	
	@Query(value="SELECT * FROM travel_detail ORDER BY hit DESC LIMIT 5", nativeQuery=true)
	public List<TravelEntity> travelHitList();
	
	@Query(value="SELECT * FROM travel_detail ORDER BY rand() limit 12", nativeQuery=true)
	public List<TravelEntity> travelRand();
	
	public TravelEntity findByNo(int no);
}
