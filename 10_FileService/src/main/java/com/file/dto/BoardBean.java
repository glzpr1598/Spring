package com.file.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("BoardBean")
public class BoardBean {
	
	private int idx;
	private String user_name;
	private String subject;
	private String content;
	private int bhit;
	private Date reg_date;
	
	public BoardBean() {
		
	}
	public BoardBean(String user_name, String subject, String content) {
		this.user_name = user_name;
		this.subject = subject;
		this.content = content;
	}
	public BoardBean(int idx, String user_name, String subject, String content) {
		this.idx = idx;
		this.user_name = user_name;
		this.subject = subject;
		this.content = content;
	}
	// Getters, Setters
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBhit() {
		return bhit;
	}
	public void setBhit(int bhit) {
		this.bhit = bhit;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

}
