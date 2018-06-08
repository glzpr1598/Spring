package com.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.file.dao.SqlInter;
import com.file.dto.BoardBean;

@Service
public class FileService {
	
	@Autowired
	SqlSession sqlSession;
	SqlInter inter;
	// 업로드할 파일명 리스트(new, old)
	HashMap<String, String> fileList = new HashMap<>();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ModelAndView upload(MultipartFile file, String root) {
		ModelAndView mav = new ModelAndView();
		String fullPath = root + "resources/upload/";
		logger.info(fullPath);
		
		// 디렉토리가 없을 경우 생성
		File dir = new File(fullPath);
		if(!dir.exists()) {
			logger.info("디렉토리 생성");
			dir.mkdirs();
		}
		
		// 파일명 가져오기
		String fileName = file.getOriginalFilename();
		
		// 새로운 파일명 생성
		String newFileName = System.currentTimeMillis() + 
				fileName.substring(fileName.lastIndexOf('.'));
		
		// 파일 추출
		try {
			byte[] bytes = file.getBytes();
			Path filePath = Paths.get(fullPath + newFileName);  // 파일 생성 경로
			Files.write(filePath, bytes);  // 파일 생성
			
			fileList.put(newFileName, fileName);
			logger.info("저장할 파일 개수 : {}", fileList.size());
			
			mav.addObject("path", "resources/upload/"+newFileName);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		mav.setViewName("uploadForm");
		return mav;
	}

	public HashMap<String, Object> fileDel(String root, String fileName) {
		HashMap<String, Object> map = new HashMap<>();
		int success = 0;
		String fullPath = root+"resources/upload/"+fileName;
		File file = new File(fullPath);
		
		if(file.exists()) {
			file.delete();
		} else {
			logger.info("이미 삭제된 사진");
		}
		
		if(fileList.get(fileName) != null) {
			fileList.remove(fileName);
			logger.info("삭제 완료");
			logger.info("남은 파일 개수 : {}", fileList.size());
			success = 1;
		}
		map.put("success", success);
		
		return map;
	}

	@Transactional
	public ModelAndView write(HashMap<String, String> params) {
		String page = "redirect:/writeForm";
		
		String user_name = params.get("userName");
		String subject = params.get("subject");
		String content = params.get("content");
		logger.info("content : {}",content);
		BoardBean bean = new BoardBean(user_name, subject, content);
		
		inter = sqlSession.getMapper(SqlInter.class);
		int success = inter.write(bean);
		
		if (success > 0) {
			page = "redirect:/detail?idx="+bean.getIdx();
			
			if(fileList.size() > 0) {
				for(String key: fileList.keySet()) {
					inter.writeFile(key, fileList.get(key), bean.getIdx());
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(page);
		return mav;
	}

}
