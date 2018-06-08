package com.file.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.file.service.FileService;

@Controller
public class HomeController {
	
	@Autowired
	FileService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("홈 요청");
		return "home";
	}
	
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		logger.info("글쓰기 페이지 요청");
		return "writeForm";
	}
	
	@RequestMapping(value = "/uploadForm")
	public String uploadForm() {
		logger.info("업로드 페이지 요청");
		return "uploadForm";
	}
	
	@RequestMapping(value = "/upload")
	public ModelAndView upload(MultipartFile file, HttpSession session) {
		logger.info("업로드 요청");
		String root = session.getServletContext().getRealPath("/");
		
		return service.upload(file, root);
	}
	
	@RequestMapping(value = "/write")
	public ModelAndView write(@RequestParam HashMap<String, String> params) {
		logger.info("글쓰기 요청");
		
		return service.write(params);
	}
	
	@RequestMapping(value = "/detail")
	public ModelAndView detail() {
		logger.info("상세보기 요청");
		return null;
	}
	
	@RequestMapping(value = "/fileDel")
	public @ResponseBody HashMap<String, Object> fileDel(@RequestParam("fileName") String fileName, HttpSession session) {
		logger.info("파일 삭제 요청");
		String root = session.getServletContext().getRealPath("/");
		return service.fileDel(root, fileName);
	}
	
	@RequestMapping(value = "/download")
	public void download() {
		logger.info("다운로드 요청");
	}
	
}
