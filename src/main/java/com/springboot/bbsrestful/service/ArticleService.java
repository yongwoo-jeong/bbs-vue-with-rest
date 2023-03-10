package com.springboot.bbsrestful.service;

import com.springboot.bbsrestful.repository.ArticleRepository;
import com.springboot.bbsrestful.repository.CommentRepository;
import com.springboot.bbsrestful.repository.FileRepository;
import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.vo.CategoryVO;
import com.springboot.bbsrestful.vo.CommentVO;
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
	 * 코멘트 레포지토리 생성자
	 */
	private final CommentRepository commentRepository;

	/**
	 * 파일 레포지토리 생성자
	 */
	private final FileRepository fileRepository;

	/**
	 * 카테고리 리스트 위한 메서드
	 * @return List of Categories
	 */
	public List<CategoryVO> selectCategories(){
		return articleRepository.selectCategories();
	}

	/**
	 * 홈화면을 보여주기 위해 검색된 게시글(혹은 전체게시글)과
	 * 게시글 수 카운트를 가져오는 서비스
	 * @param searchCriteria 검색조건(없을 경우 쿼리문 TRIM 되어 전체게시글 검색)
	 * @return List of article VO (검색된 게시글)
	 */
	public List<ArticleVO> selectArticleList(SearchCriteriaVO searchCriteria){
		return articleRepository.selectSearchArticles(searchCriteria);
	}

	/**
	 * 조건에 맞는 게시글 개수를 가져오는 서비스
	 * @param searchCriteria 검색조건(없을 경우 쿼리문 TRIM 되어 전체게시글 검색)
	 * @return 검색된 게시글 수
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
		articleRepository.updateViewCount(articleId);
		return articleRepository.selectArticle(articleId);
	}

	/**
	 * 새 게시글 등록 서비스
	 * TODO enum 예외처리
	 * @param newArticle 새 게시글 정보가 담긴 객체
	 */
	public int insertNewArticle(ArticleVO newArticle, String passwordConfirm) {
//		ArticleValidationResult validationResult = validateArticleForm(newArticle, passwordConfirm);
//		if (validationResult.getStatus()!=201){
//			throw new FormValidException(validationResult.getStatus(), validationResult.getMessage());
//		}
		// articleInput.jsp select value 로 받은 "1-JAVA" 형태 스트링을
		// 스플릿해서 categoryId, categoryName 으로 사용
		ArticleVO articleInserting = ArticleVO.builder().title(newArticle.getTitle()).writer(newArticle.getWriter())
				.password(newArticle.getPassword()).content(newArticle.getContent())
				.categoryId(newArticle.getCategoryId()).build();
		articleRepository.insertArticle(articleInserting);
		return articleInserting.getArticleId();
	}

	/**
	 * 게시글에 딸린 댓글 리스트를 컨트롤러로 보내주는 서비스
	 * @param articleId 해당 게시글 id
	 * @return
	 */
	public List<CommentVO> selectCommentList(Integer articleId){
		return commentRepository.selectComments(articleId);
	}

	/**
	 * 새 댓글 추가 서비스 메서드
	 * @param articleId 댓글 입력 대상 게시글 ID
	 * @param newComment 새 댓글 객체
	 */
	public void insertNewComment(Integer articleId, CommentVO newComment){
		newComment.setArticleId(articleId);
		commentRepository.insertComment(newComment);
	}

	/**
	 * 게시글을 삭제해주는 서비스컴포넌트
	 * 관련 파일, 댓글들도 모두 삭제
	 * @param articleId
	 */
	public void deleteArticle(Integer articleId){
		commentRepository.deleteComments(articleId);
		fileRepository.deleteFiles(articleId);
		articleRepository.deleteArticle(articleId);
	}
}

