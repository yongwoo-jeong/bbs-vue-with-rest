package com.springboot.bbsrestful.controller;


import com.springboot.bbsrestful.service.FileService;
import com.springboot.bbsrestful.vo.FileVO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 파일 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081/")
public class FileController {

	/**
	 * 파일 서비스 생성자 DI
	 */
	private final FileService fileService;

	/**
	 * 파일 다운로드 url 컨트롤러
	 */
	@GetMapping("/api/v1/file/{fileId}")
	public ResponseEntity<Resource> fileDownloadController(@PathVariable("fileId") String fileId)
			throws IOException {
		FileVO targetFile = fileService.getTargetFileResource(fileId);
		File file = new File(targetFile.getFilePath(), targetFile.getNameOnServer());
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.contentLength(file.length())
				.body(resource);
	}

}
