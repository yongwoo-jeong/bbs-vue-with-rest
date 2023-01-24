package com.springboot.bbsrestful.service;

import com.springboot.bbsrestful.repository.ArticleRepository;
import com.springboot.bbsrestful.utils.CustomException;
import com.springboot.bbsrestful.utils.StringUtils;
import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.vo.CategoryVO;
import com.springboot.bbsrestful.vo.SearchCriteriaVO;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
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

	/**
	 * 게시글 상세
	 * @param articleId 게시글 id
	 * @return 게시글 VO
	 */
	public ArticleVO selectArticle(Integer articleId){
		return articleRepository.selectArticle(articleId);
	}

	/**
	 * 새 게시글 등록 서비스
	 * TODO enum 예외처리 활용..
	 * @param newArticle 새 게시글 정보가 담긴 객체
	 */
	public int insertNewArticle(ArticleVO newArticle, String passwordConfirm) throws CustomException {
		ArticleValidationResult validationResult = validateArticleForm(newArticle, passwordConfirm);
		if (validationResult.getStatus()!=201){
			throw new CustomException(validationResult.getStatus(), validationResult.getMessage());
		}
		// articleInput.jsp select value 로 받은 "1-JAVA" 형태 스트링을
		// 스플릿해서 categoryId, categoryName 으로 사용
		ArticleVO articleInserting = ArticleVO.builder().title(newArticle.getTitle()).writer(newArticle.getWriter())
				.password(newArticle.getPassword()).content(newArticle.getContent())
				.categoryId(newArticle.getCategoryId()).build();
		articleRepository.insertArticle(articleInserting);
		return articleInserting.getArticleId();
	}

	/**
	 * 게시글 폼데이터 양식 검증
	 * @param newArticle 새 게시글 객체
	 * @param passwordConfirm 비밀번호 확인은 VO 포함되지 않아 따로 검증
	 * @return ArticleValidationResult ENUM
	 */
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


	/**
	 * 게시글 검증 상태코드
	 */
	@Getter
	@RequiredArgsConstructor
	public enum ArticleValidationResult{
		CREATED(201,"Article Created"),
		CATEGORY_NOT_SELECTED(422, "Category must be selected"),
		PASSWORD_NOT_MATCH(422, "Password is not confirmed"),
		WRITER_OUT_OF_RANGE(422, "Writer length should be 3~4 letters"),
		TITLE_OUT_OF_RANGE(422, "Title length should be 4~100 letters"),
		CONTENT_OUT_OF_RANGE(422, "Content length should be 4~2000 letters");

		/**
		 * HTTP status code
		 */
		private final int status;

		/**
		 * 게시글 양식 검증 결과
		 */
		private final String message;
	}


}

