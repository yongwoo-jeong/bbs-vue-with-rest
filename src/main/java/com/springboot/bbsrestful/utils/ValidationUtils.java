package com.springboot.bbsrestful.utils;

import com.springboot.bbsrestful.vo.ArticleVO;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ValidationUtils {
	public static ArticleValidationResult validateArticleForm(ArticleVO newArticle, String passwordConfirm){
		// 게시판 등록항목 검증
		if (!Objects.equals(newArticle.getPassword(), passwordConfirm)){
			return ArticleValidationResult.PASSWORD_NOT_MATCH;
		}
		if (newArticle.getWriter().length()> 4 || newArticle.getWriter().length()<3){
			return ArticleValidationResult.WRITER_OUT_OF_RANGE;
		}
		if (StringUtils.isEmpty(newArticle.getCategoryId())){
			return ArticleValidationResult.CATEGORY_NOT_SELECTED;
		}
		if (newArticle.getTitle().length() < 3 || newArticle.getTitle().length() > 100){
			return ArticleValidationResult.TITLE_OUT_OF_RANGE;
		}
		if (newArticle.getContent().length()<4 || newArticle.getContent().length()>2000){
			return ArticleValidationResult.CONTENT_OUT_OF_RANGE;
		}
		return ArticleValidationResult.CREATED;
	}


	@Getter
	@RequiredArgsConstructor
	public enum ArticleValidationResult{
		CREATED(201,"Article Created"),
		CATEGORY_NOT_SELECTED(422, "Category must be selected"),
		PASSWORD_NOT_MATCH(422, "Password is not confirmed"),
		WRITER_OUT_OF_RANGE(422, "Writer length should be 3~4 letters"),
		TITLE_OUT_OF_RANGE(422, "Title length should be 4~100 letters"),
		CONTENT_OUT_OF_RANGE(422, "Content length should be 4~2000 letters");

		private final int status;
		private final String message;




	}
}
