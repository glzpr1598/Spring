package com.file.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

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
import com.file.dto.FileBean;

@Service
public class FileService {
	
	@Autowired
	SqlSession sqlSession;
	SqlInter inter;

	// 업로드할 파일명 리스트(new, old)
	HashMap<String, String> fileList = new HashMap<>();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 글 리스트
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		inter = sqlSession.getMapper(SqlInter.class);

		mav.addObject("list", inter.list());
		mav.setViewName("list");
		
		return mav;
	}

	// 업로드
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
			fileList.clear();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(page);
		return mav;
	}

	// 상세보기
	public ModelAndView detail(String idx) {
		ModelAndView mav = new ModelAndView();
		inter = sqlSession.getMapper(SqlInter.class);
		
		// 글 정보
		mav.addObject("dto", inter.detail(idx));
		// 파일 정보
		ArrayList<FileBean> fileList = inter.fileList(idx); 
		mav.addObject("files", fileList);  // 파일 리스트
		mav.addObject("size", fileList.size());  // 파일 개수
		
		mav.setViewName("detail");
		return mav;
	}

	// 파일 다운로드
	public void download(String root, String file, HttpServletResponse response) throws IOException {
		String fullPath = root + "resources/upload/" + file;
		
		// 파일 가져오기
		Path path = Paths.get(fullPath);
		byte[] bytes = Files.readAllBytes(path);
		
		// 원본 파일명 가져오기
		inter = sqlSession.getMapper(SqlInter.class);
		String oldFile = inter.downloadName(file);
		
		// response 객체에 파일 담기
		String downFile = URLEncoder.encode(oldFile, "UTF-8");  // 한글 처리
		response.setContentType("application/octet-stream");
		response.setHeader("content-Disposition", 
				"attachment; filename=\""+downFile+"\"");
		
		// response 쓰기
		OutputStream os = response.getOutputStream();
		os.write(bytes);
		
		// 자원 반납
		os.flush();
		os.close();
	}

	// 수정 폼
	public ModelAndView modifyForm(String idx) {
		ModelAndView mav = new ModelAndView();
		inter = sqlSession.getMapper(SqlInter.class);
		
		// fileList 가져오기
		ArrayList<FileBean> list = inter.fileList(idx);
		for(FileBean file: list) {
			fileList.put(file.getNewFile(), file.getOldFile());
		}
		
		mav.addObject("dto", inter.detail(idx));
		mav.setViewName("modifyForm");
		
		return mav;
	}

	// 수정
	public ModelAndView modify(HashMap<String, String> params) {
		int idx = Integer.parseInt(params.get("idx")); 
		inter = sqlSession.getMapper(SqlInter.class);
		
		// 글 수정
		int success = inter.modify(params);
		
		// 기존 파일 모두 지우기
		inter.deleteFile(idx);
		
		// 파일 쓰기
		if (success > 0) {
			if(fileList.size() > 0) {
				for(String key: fileList.keySet()) {
					inter.writeFile(key, fileList.get(key), idx);
				}
			}
			fileList.clear();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:detail?idx="+idx);
		return mav;
	}

	// 삭제
	public ModelAndView delete(String idx) {
		ModelAndView mav = new ModelAndView();
		
		// 글 삭제
		inter.delete(idx);
		// 파일 삭제
		inter.deleteFile(Integer.parseInt(idx));
		
		mav.setViewName("redirect:/");
		return mav;
	}

}
