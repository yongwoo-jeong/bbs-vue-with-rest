package com.springboot.bbsrestful.repository;

import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.vo.CategoryVO;
import com.springboot.bbsrestful.vo.SearchCriteriaVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * 서비스단에서 요청받아 DB 데이터 반환하는
 * 아티클 레포지토리
 */
@Repository
@RequiredArgsConstructor
public class ArticleRepository {

	/**
	 * 매퍼 네임스페이스 반복구문 스트링 처리
	 */
	private final String mapperName = "com.springboot.bbsrestful.repository.ArticleRepository";

	/**
	 * sql 세션객체 생성자
	 */
	private final SqlSession session;

	/**
	 * 조건에 맞는 게시글객체를 List 형태로 반환
	 * @param searchCriteria 검색조건 (searchCriteriaVO)
	 * @return List of ArticleVO
	 */
	public List<ArticleVO> selectSearchArticles(SearchCriteriaVO searchCriteria){
		return session.selectList(mapperName + ".selectSearchArticles", searchCriteria);
	};

	/**
	 * 조건에 맞는 게시글 수 SELECT COUNT
	 * @param searchCriteria 검색조건 (searchCriteriaVO)
	 * @return 검색된 게시글 수
	 */
	public Integer selectCountArticles(SearchCriteriaVO searchCriteria){
		return session.selectOne(mapperName + ".selectCountArticles",searchCriteria);
	};

	/**
	 * 카테고리 리스트 가져오기 SELECT
	 * @return
	 */
	public List<CategoryVO> selectCategories(){
		return session.selectList(mapperName+".selectCategories");
	}

	/**
	 * 게시글 상세 SELECT
	 * @param articleId 게시글 id
	 * @return
	 */
	public ArticleVO selectArticle(Integer articleId){
		return session.selectOne(mapperName+".selectArticleDetail", articleId);
	}

}
