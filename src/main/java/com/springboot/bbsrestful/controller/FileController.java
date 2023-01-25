package com.springboot.bbsrestful.controller;


import com.springboot.bbsrestful.service.FileService;
import com.springboot.bbsrestful.vo.FileVO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
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
	public ResponseEntity<Resource> fileDownloadController(@PathVariable("fileId") String fileId, HttpServletResponse res)
			throws IOException {
		FileVO targetFile = fileService.getTargetFileResource(fileId);
		File file = new File(targetFile.getFilePath(), targetFile.getNameOnServer());
		HttpHeaders headers = new HttpHeaders();
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		String encoded = URLEncoder.encode(targetFile.getNameOriginal(), StandardCharsets.UTF_8);
		String encodedFileName = new String(targetFile.getNameOriginal().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

		System.out.println(targetFile.getNameOriginal());
		System.out.println(encoded);
		System.out.println(encodedFileName);
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=" + URLEncoder.encode(targetFile.getNameOriginal(), StandardCharsets.UTF_8));

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.contentLength(file.length())
				.headers(headers)
				.body(resource);
	}

}
