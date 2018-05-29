package com.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.dto.CrudDTO;

public class CrudDAO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 생성자(DB 접속)
	public CrudDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
			
			logger.info("DB 접속 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 자원 반납
	public void resClose() {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 리스트 가져오기
	public ArrayList<CrudDTO> list() {
		ArrayList<CrudDTO> list = new ArrayList<CrudDTO>();
		String sql = "select * from bbs order by idx desc";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CrudDTO dto = new CrudDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setbHit(rs.getInt("bHit"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return list;
	}

	public int write(CrudDTO dto) {
		int success = 0;
		String sql = "insert into bbs(idx, user_name, subject, content, bHit)"
				+ "values(bbs_seq.NEXTVAL, ?, ?, ?, 0)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUser_name());
			ps.setString(2, dto.getSubject());
			ps.setString(3, dto.getContent());
			success = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return success;
	}

	public CrudDTO contentView(int id) {
		CrudDTO dto = new CrudDTO();
		String sql = "select * from bbs where idx=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				logger.info("글 조회 성공");
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setbHit(rs.getInt("bHit"));
				dto.setContent(rs.getString("content"));
			} else {
				logger.info("글 조회 실패");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return dto;
	}

}
