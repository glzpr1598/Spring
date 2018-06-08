package com.file.dao;

import com.file.dto.BoardBean;

public interface SqlInter {

	int write(BoardBean bean);

	void writeFile(String key, String string, int idx);

}
