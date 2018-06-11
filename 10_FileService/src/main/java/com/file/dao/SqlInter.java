package com.file.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.file.dto.BoardBean;
import com.file.dto.FileBean;

public interface SqlInter {

	ArrayList<BoardBean> list();

	int write(BoardBean bean);

	void writeFile(String key, String string, int idx);

	BoardBean detail(String idx);

	ArrayList<FileBean> fileList(String idx);

	String downloadName(String file);

	BoardBean modifyForm(String idx);

	int modify(HashMap<String, String> params);

	void deleteFile(int idx);

	void delete(String idx);


}
