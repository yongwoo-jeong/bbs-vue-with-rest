package com.springboot.bbsrestful.dto;

import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.vo.CategoryVO;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * 게시글 리스트페이지(루트)를 위한 VO
 */
@Data
@Builder
public class BoardDTO {

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
