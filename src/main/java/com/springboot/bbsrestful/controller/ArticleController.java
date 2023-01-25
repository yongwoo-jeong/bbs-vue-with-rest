package com.springboot.bbsrestful.controller;

import com.springboot.bbsrestful.service.ArticleService;
import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.vo.BoardVO;
import com.springboot.bbsrestful.vo.CategoryVO;
import com.springboot.bbsrestful.vo.CommentVO;
import com.springboot.bbsrestful.vo.SearchCriteriaVO;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@Value("${dev.file.localPath}")
	private String serverFilePath;

	/**
	 * GET /articles
	 * 게시글 리스트, 게시글 수, 카테고리 리스트 반환
	 * TODO 파일 이너 조인으로 가져와야 파일 표시가능 -> 파일 여부를 컬럼으로 만들어주는게..
	 * @param searchCriteria 검색조건, 조건이 없을 경우 아티클매퍼에서 trim 처리로 조건 제외
	 * @return boardData 보드 VO
	 */
	@GetMapping("/api/v1/articles")
	public BoardVO articleListController(@ModelAttribute SearchCriteriaVO searchCriteria){
		// 카테고리명
		List<CategoryVO> categories = articleService.selectCategories();
		// DB SELECT LIMIT offset 설정
		searchCriteria.setDbLimitOffset((searchCriteria.getCurrentPage()-1)*10);
		// 게시글리스트, 게시글 숫자 담은 DTO
		List<ArticleVO> searchedArticles = articleService.selectArticleList(searchCriteria);
		int countArticles =  articleService.selectSearchedArticleCount(searchCriteria);
		BoardVO boardData = BoardVO.builder().articleList(searchedArticles).searchedArticleCount(countArticles).categoryList(categories).build();
		return boardData;
	}

	/**
	 * GET /articles 게시글 상세, 댓글, 파일 리스트 반환
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/api/v1/articles/{id}")
	public HashMap<String, Object> articleDetailController(@PathVariable("id") Integer id){
		ArticleVO articleDetail = articleService.selectArticle(id);
		List<CommentVO> commentList = articleService.selectCommentList(id);
		HashMap<String,Object> detailPageSet = new HashMap<>();
		detailPageSet.put("articleDetail",articleDetail);
		detailPageSet.put("commentList",commentList);
		return detailPageSet;
	}

	/**
	 * 폼을 통해 받은 새 게시글 정보 POST 요청 처리
	 * TODO ENUM 통한 에러코드 고민
	 * @return
	 */
	@PostMapping(value = "/api/v1/articles")
	public void insertArticleController(@ModelAttribute ArticleVO newArticle,
//										@RequestParam("file") FileVO file,
										@RequestParam("passwordConfirm") String passwordConfirm)
//										@ModelAttribute SearchCriteriaVO searchCriteria)
										{
		articleService.insertNewArticle(newArticle, passwordConfirm);
//		String searchQueryString = req.getQueryString();
	}

	/**
	 * 새 댓글 POST 요청 컨트롤러
	 * @param articleId
	 * @param newComment
	 */
	@PostMapping("/api/v1/articles/{id}/comments")
	public void insertComment(@PathVariable("id") Integer articleId,@ModelAttribute CommentVO newComment){
		articleService.insertNewComment(articleId,newComment);
		// 댓글 POST 요청 후 이전 페이지로 돌리기 위한 referer
//		return "redirect:/article?"+req.getQueryString();
	}
}
