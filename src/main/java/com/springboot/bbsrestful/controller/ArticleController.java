package com.springboot.bbsrestful.controller;

import com.springboot.bbsrestful.service.ArticleService;
import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.vo.BoardVO;
import com.springboot.bbsrestful.vo.CategoryVO;
import com.springboot.bbsrestful.vo.SearchCriteriaVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * 게시글 관련 요청 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ArticleController {

	/**
	 * 게시글 관련 서비스 객체
	 */
	private final ArticleService articleService;

	@Value("${dev.file.localPath}")
	private String serverFilePath;

	/**
	 * 홈페이지(/) GET 매핑
	 * TODO 파일 이너 조인으로 가져와야 파일 표시가능
	 *
	 * @param model          애트리뷰트 설정을 위한 모델객체
	 * @param searchCriteria 검색조건, 조건이 없을 경우 아티클매퍼에서 trim 처리로 조건 제외
	 * @return boardData 보드 VO
	 */
	@GetMapping("/api/v1/articles")
	public BoardVO homeController(Model model, @ModelAttribute SearchCriteriaVO searchCriteria){
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

}
