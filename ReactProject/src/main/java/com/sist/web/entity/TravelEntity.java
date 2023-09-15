package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
NO bigint 
TITLE varchar(100) 
TAG varchar(1000) 
INTRODUCTION varchar(1000) 
LOC varchar(100) 
ADDR varchar(200) 
ROAD varchar(200) 
TEL varchar(60) 
POSTER varchar(500) 
INFO longblob 
LNO bigint 
TCNO bigint 
HIT
 */
@Entity
@Table(name="travel_detail")
@Getter
@Setter
public class TravelEntity {
	@Id
	private int no;
	private int lno,tcno,hit;
	private String INFO;
	private String title,tag,introduction,loc,addr,road,tel,poster;
}
