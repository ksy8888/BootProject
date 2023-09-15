package com.sist.web.dao;
import java.util.List;
import com.sist.web.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardDAO  extends JpaRepository<BoardVO, Integer> {
	@Query(value="SELECT * FROM board ORDER BY no DESC LIMIT :start,10",nativeQuery = true)
	public List<BoardVO> boardListData(@Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/10.0) FROM board")
    public int boardTotalPage();
	
//	@Query(value="SELECT COUNT(*) FROM board")
//    public int boardCount();
	@Query(value="SELECT * FROM board ORDER BY hit DESC LIMIT 5",nativeQuery = true)
	public List<BoardVO> boardHitListData();
	
	public BoardVO findByNo(@Param("no") Integer no);// 상세보기 
}
