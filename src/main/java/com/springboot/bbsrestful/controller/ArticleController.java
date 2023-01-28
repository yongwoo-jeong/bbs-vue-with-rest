package com.springboot.bbsrestful.controller;

import com.springboot.bbsrestful.dto.ArticleDetailDTO;
import com.springboot.bbsrestful.service.ArticleService;
import com.springboot.bbsrestful.service.FileService;
import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.dto.BoardDTO;
import com.springboot.bbsrestful.vo.CategoryVO;
import com.springboot.bbsrestful.vo.CommentVO;
import com.springboot.bbsrestful.vo.FileVO;
import com.springboot.bbsrestful.vo.SearchCriteriaVO;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 게시글 관련 요청 컨트롤러
 */
@RestController
@CrossOrigin(origins = "http://localhost:8081/")
@RequiredArgsConstructor
public class ArticleController {

	/**
	 * 게시글 관련 서비스 객체
	 */
	private final ArticleService articleService;

	/**
	 * 파일 서비스 객체
	 */
	private final FileService fileService;

	@Value("${dev.file.localPath}")
	private String serverFilePath;

	/**
	 * GET /articles 게시글 리스트, 게시글 수, 카테고리 리스트 반환
	 * TODO 파일 이너 조인으로 가져와야 파일 표시가능 -> 파일 여부를 컬럼으로 만들어주는게..
	 *
	 * @param searchCriteria 검색조건, 조건이 없을 경우 아티클매퍼에서 trim 처리로 조건 제외
	 * @return boardData 보드 VO
	 */
	@GetMapping("/api/v1/articles")
	public BoardDTO articleListController(@ModelAttribute SearchCriteriaVO searchCriteria) {
		// 카테고리명
		List<CategoryVO> categories = articleService.selectCategories();
		// DB SELECT LIMIT offset 설정
		searchCriteria.setDbLimitOffset((searchCriteria.getCurrentPage() - 1) * 10);
		// 게시글리스트, 게시글 숫자 담은 DTO
		List<ArticleVO> searchedArticles = articleService.selectArticleList(searchCriteria);
		int countArticles = articleService.selectSearchedArticleCount(searchCriteria);
		BoardDTO boardData = BoardDTO.builder().articleList(searchedArticles)
				.searchedArticleCount(countArticles).categoryList(categories).build();
		return boardData;
	}

	/**
	 * GET /articles 게시글 상세, 댓글, 파일 리스트 반환
	 *
	 * @param articleId
	 * @return
	 */
	@GetMapping("/api/v1/articles/{id}")
	public ArticleDetailDTO articleDetailController(@PathVariable("id") Integer articleId) {
		ArticleVO articleDetail = articleService.selectArticle(articleId);
		List<CommentVO> commentList = articleService.selectCommentList(articleId);
		List<FileVO> fileList = fileService.getFileList(articleId);
		ArticleDetailDTO articleDetailDto = ArticleDetailDTO.builder()
				.articleDetail(articleDetail)
				.commentList(commentList)
				.fileList(fileList).build();
		return articleDetailDto;
	}

	/**
	 * 폼을 통해 받은 새 게시글 정보 POST 요청 처리
	 * TODO ENUM 통한 에러코드 고민
	 *
	 * @return
	 */
	@PostMapping(value = "/api/v1/articles")
	public void insertArticleController(@ModelAttribute ArticleVO newArticle,
			@RequestPart("file") List<MultipartFile> files,
			@RequestParam("passwordConfirm") String passwordConfirm) {
		Integer articleId = articleService.insertNewArticle(newArticle, passwordConfirm);
		fileService.insertNewFiles(files, articleId);
//		String searchQueryString = req.getQueryString();
	}

	/**
	 * 새 댓글 POST 요청 컨트롤러
	 *
	 * @param articleId
	 * @param newComment
	 */
	@PostMapping("/api/v1/articles/{id}/comments")
	public void insertComment(@PathVariable("id") Integer articleId,
			@ModelAttribute CommentVO newComment) {
		articleService.insertNewComment(articleId, newComment);
	}

	/**
	 * 게시글 삭제 컨트롤러
	 *
	 * @param articleId         게시글 id
	 */
	@DeleteMapping("/api/v1/articles/{id}")
	public void deleteArticleController(@PathVariable("id") Integer articleId){
		articleService.deleteArticle(articleId);
//	    need to check password from server
//		System.out.println(userInputPassword);
	}

}
