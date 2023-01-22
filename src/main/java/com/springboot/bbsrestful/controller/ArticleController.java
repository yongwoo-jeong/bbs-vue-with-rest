package com.springboot.bbsrestful.controller;

import com.springboot.bbsrestful.service.ArticleService;
import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.vo.BoardVO;
import com.springboot.bbsrestful.vo.CategoryVO;
import com.springboot.bbsrestful.vo.SearchCriteriaVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		System.out.println(searchCriteria.getCurrentPage());
		// DB SELECT LIMIT offset 설정
		searchCriteria.setDbLimitOffset((searchCriteria.getCurrentPage()-1)*10);
		// 게시글리스트, 게시글 숫자 담은 DTO
		List<ArticleVO> searchedArticles = articleService.selectArticleList(searchCriteria);
		int countArticles =  articleService.selectSearchedArticleCount(searchCriteria);
		BoardVO boardData = BoardVO.builder().articleList(searchedArticles).searchedArticleCount(countArticles).categoryList(categories).build();
		return boardData;
	}

	/**
	 * GET /articles
	 * 게시글 상세, 댓글, 파일 리스트 반환
	 * @param id
	 * @return
	 */
	@GetMapping("/api/v1/articles/{id}")
	public ArticleVO articleDetailController(@PathVariable("id") Integer id){
		ArticleVO articleDetail = articleService.selectArticle(id);
		return articleDetail;
	}

}
