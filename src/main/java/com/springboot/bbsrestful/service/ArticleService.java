package com.springboot.bbsrestful.service;

import com.springboot.bbsrestful.repository.ArticleRepository;
import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.vo.CategoryVO;
import com.springboot.bbsrestful.vo.SearchCriteriaVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 게시글 관련 서비스객체
 */
@Service
@RequiredArgsConstructor
public class ArticleService {

	/**
	 * 게시글 관련 데이터 리포지토리 객체
	 */
	private final ArticleRepository articleRepository;

	/**
	 * 카테고리 리스트 위한 메서드
	 * @return
	 */
	public List<CategoryVO> selectCategories(){
		return articleRepository.selectCategories();
	}

	/**
	 * 홈화면을 보여주기 위해 검색된 게시글(혹은 전체게시글)과
	 * 게시글 수 카운트를 가져오는 서비스
	 * @param searchCriteria 검색조건(없을 경우 쿼리문 TRIM 되어 전체게시글 검색)
	 * @return
	 */
	public List<ArticleVO> selectArticleList(SearchCriteriaVO searchCriteria){
		return articleRepository.selectSearchArticles(searchCriteria);
	}

	/**
	 * 검색된 게시글 수
	 * @param searchCriteria 검색조건(없을 경우 쿼리문 TRIM 되어 전체게시글 검색)
	 * @return
	 */
	public int selectSearchedArticleCount(SearchCriteriaVO searchCriteria){
		return articleRepository.selectCountArticles(searchCriteria);
	}





}

