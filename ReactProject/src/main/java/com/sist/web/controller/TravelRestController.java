package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.TravelDAO;
import com.sist.web.entity.*;
@RestController
@RequestMapping("travel/")
@CrossOrigin("http://localhost:3000")
public class TravelRestController {
	@Autowired
	private TravelDAO dao;  
	
	@GetMapping("travel_list_react")
	public List<TravelEntity> travel_list(String title,String page) {
		if(title==null) {
			title="제주";
		}
		if(page==null) {
			page="1";
		}
		int curpage = Integer.parseInt(page);
		int rowSize = 20;
		int start = (rowSize*curpage)-rowSize;
		List<TravelEntity> list = dao.travelListData(title,start);
		return list;
	}
	
	@GetMapping("travel_rand")
	public List<TravelEntity> travel_rand() {
		List<TravelEntity> list = dao.travelRand();
		return list;
	}
	
	@GetMapping("travel_hit")
	public List<TravelEntity> travel_hit() {
		List<TravelEntity> list = dao.travelHitList();
		
		return list;
	}
	
	@GetMapping("travel_page_react")
	public PageVO travel_page(String title,int page) {
		int totalpage=dao.travelTotalPage(title);
		final int BLOCK=5;
		int startPage = ((page-1)/BLOCK*BLOCK)+1;
		int endPage = ((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
    		endPage=totalpage;
    	}
    		
    	PageVO vo = new PageVO();
    	vo.setCurpage(page);
    	vo.setEndPage(endPage);
    	vo.setStartPage(startPage);
    	vo.setTotalpage(totalpage);
    	return vo;
	}
	
	@GetMapping("travel_detail_react")
	public TravelEntity travel_detail(int no) {
		TravelEntity vo = dao.findByNo(no);
		return vo;
	}
	
	
}
