package com.springboot.bbsrestful.vo;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * 게시글 검색결과(인덱스페이지)를 위한 VO
 */
@Data
@Builder
public class BoardVO {

	/**
	 * 검색된 게시글 전체
	 */
	private List<ArticleVO> articleList;

	/**
	 * 검색된 게시글 수
	 */
	private Integer searchedArticleCount;

	/**
	 * 카테고리 리스트
	 */
	private List<CategoryVO> categoryList;


}
