package com.spring.board.service;

import java.util.List;

import com.spring.board.vo.BoardVO;
import com.spring.board.vo.PagingCriteria;

public interface BoardService {
	// 글 목록 조회
	List<BoardVO> getBoardList(PagingCriteria paging);

	// 글 상세 조회
	BoardVO getContent(BoardVO vo);

	BoardVO moveModifytBoard(BoardVO vo);

	// 글 등록
	void insertBoard(BoardVO vo);

	// 글 수정
	void updateBoard(BoardVO vo);

	// 글 삭제
	void deleteBoard(BoardVO vo);
	
	//글 갯수
		int totalCnt();
}
