package com.springboot.bbsrestful.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 관련 유틸
 */
public class FileUtils {

	/**
	 * 빈 파일인지 확인
	 * @param file MultipartFile
	 * @return boolean
	 */
	public static boolean isFileEmpty(MultipartFile file){
		// 파일명없거나(null) 파일 사이즈가 0일 경우 빈 파일
		return file.getOriginalFilename() == null || file.getSize() == 0;
	}

}
