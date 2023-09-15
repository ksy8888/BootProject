package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.dao.*;
import java.util.*;
import com.sist.web.entity.*;

@RestController
@RequestMapping("board/")
@CrossOrigin("http://localhost:3000")
public class BoardRestController {

	@Autowired
	private BoardDAO dao;
	
	@GetMapping("board_list")
	public List<BoardVO> board_list(String page) {
		if(page==null) {
			page="1";
		}
		int  curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-rowSize; // Limit => 0번부터 시작 그래서 rowSize-1(X)
		List<BoardVO> list=dao.boardListData(start);
        
		for(BoardVO vo:list)
		{
			String s=vo.getRegdate(); //2023-08-31 11:02:51
			if (s != null) {
			        String[] ss=s.split(" "); // 뒤에 시간대 없애기
			        vo.setRegdate(ss[0].trim()); // 2023-08-31
			} else {
			   vo.setRegdate(" ");
			}
//			vo.setRegdate(vo.getRegdate().split(" ")[0]);
		}
		return list;
	}
	
	@GetMapping("board_hit")
	public List<BoardVO> board_hit() {
		List<BoardVO> list = dao.boardHitListData();
		return list;
	}
	
	@GetMapping("board_page")
	public PageVO board_page(int page)
	{
		PageVO vo=new PageVO();
		final int BLOCK=5;
		int totalpage=dao.boardTotalPage();
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		vo.setCurpage(page);
		vo.setEndPage(endPage);
		vo.setStartPage(startPage);
		vo.setTotalpage(totalpage);
		return vo;
	}
	
	@GetMapping("board_detail")
	public BoardVO board_detail(int no) {
		BoardVO vo = dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		vo=dao.findByNo(no);
		String s=vo.getRegdate(); //2023-08-31 11:02:51
		if (s != null) {
		        String[] ss=s.split(" "); // 뒤에 시간대 없애기
		        vo.setRegdate(ss[0].trim()); // 2023-08-31
		} else {
		   vo.setRegdate(" ");
		}
		return vo;
	}
	
	@GetMapping("board_insert")
	public String board_insert(BoardVO vo) {
		dao.save(vo);
		return "redirect:/board/board_list";
	
	}
	
	@GetMapping("board_delete")
	public String board_delete(String pwd,int no,BoardVO vo) {
		BoardVO pvo = dao.findByNo(no);
		if(vo.getPwd().equals(pvo.getPwd())) {
			dao.delete(vo);
			return "YES";
		} else {
			return "NO";
		}

	}
	
	@GetMapping("board_update")
	public String board_update(BoardVO vo, String pwd) {
		BoardVO tvo = dao.findByNo(vo.getNo());
		if(vo.getPwd().equals(tvo.getPwd())) {
			dao.save(vo);
			System.out.println("ddddsddddd"+vo.getRegdate());
			return "YES";
		} else {
			return "NO";
		}
	} 
	
	
	//강사님 food pagenation
//	@GetMapping("food_page_react")
//	public int page(String address) {
//		return  dao.boardCount();
//		
//	}
	
	
}
