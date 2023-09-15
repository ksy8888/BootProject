package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

@Entity(name="board")
@Getter
@Setter
public class BoardVO {
	 @Id
	 private int no;
	 private String name,subject,content,pwd;
	 private String regdate;
	 private int hit;
	 
	 @PrePersist
	 public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
	 }
	 
	 
	   
	 
}
