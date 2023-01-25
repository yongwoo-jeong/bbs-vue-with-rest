package com.springboot.bbsrestful.dto;

import com.springboot.bbsrestful.vo.ArticleVO;
import com.springboot.bbsrestful.vo.CommentVO;
import com.springboot.bbsrestful.vo.FileVO;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * 게시글 상세 페이지 DTO
 */
@Data
@Builder
public class ArticleDetailDTO {

	/**
	 * 게시글 상세 정보
	 */
	@NonNull
	private ArticleVO articleDetail;
	/**
	 * 댓글 리스트
	 */
	private List<CommentVO> commentList;
	/**
	 * 파일 리스트
	 */
	private List<FileVO> fileList;
}
