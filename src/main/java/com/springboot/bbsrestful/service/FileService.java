package com.springboot.bbsrestful.service;

import com.springboot.bbsrestful.repository.FileRepository;
import com.springboot.bbsrestful.utils.FileUtils;
import com.springboot.bbsrestful.vo.FileVO;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 서비스 컴포넌트
 */
@Service
@RequiredArgsConstructor
public class FileService {

	/**
	 * 파일 저장될 서버경로
	 */
	@Value("${dev.file.localPath}")
	private String FILEPATH;

	/**
	 * 파일레포지토리 생성자
	 */
	private final FileRepository fileRepository;


	/**
	 * 파일 추가 서비스
	 * @param fileList List of MultipartFile
	 * @param articleId 게시글 id
	 */
	public void insertNewFiles(List<MultipartFile> fileList, Integer articleId) {
		if (fileList == null){
			return;
		}
		for (MultipartFile file : fileList){
			if (FileUtils.isFileEmpty(file)) {
				continue;
			}
			// 유저가 업로드한 파일명
			String fileNameOriginal = file.getOriginalFilename();
			// 파일명 + 현재시간으로 서버에 저장될 파일명 결정
			String fileNameServer = file.getOriginalFilename().split("\\.")[0]+(new Date()).toString().replace(" ","");
			String filePath = FILEPATH;
			BigInteger fileSize = BigInteger.valueOf(file.getSize());
			// 파일 확장자명
			String fileExtension = file.getOriginalFilename().split("\\.")[1];
			// DB에 저장될 파일 객체생성
			FileVO newFile = FileVO.builder()
									.nameOnServer(fileNameServer)
									.nameOriginal(fileNameOriginal)
									.articleId(articleId).filePath(filePath)
									.fileSize(fileSize).fileExtension(fileExtension)
									.build();
			// 서버 저장될 파일
			File fileToServer = new File(filePath+fileNameServer);
			try {
				file.transferTo(fileToServer);
				fileRepository.insertFile(newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 게시글에 따른 파일 리스트 제공 서비스
	 * @param articleId 게시글 id
	 * @return
	 */
	public List<FileVO> getFileList(Integer articleId){
		return fileRepository.selectFiles(articleId);
	}

	/**
	 * 다운로드 타겟 파일 튜플 정보제공
	 * @param fileUuid
	 * @return
	 */
	public FileVO getTargetFileResource(String fileUuid) {
		return fileRepository.selectFile(fileUuid);
	}

	/**
	 * 특정 파일 삭제 서비스
	 * @param fileUuid 파일 uuid
	 */
	public void deleteFile(String fileUuid){
		fileRepository.deleteFile(fileUuid);
	}
}
