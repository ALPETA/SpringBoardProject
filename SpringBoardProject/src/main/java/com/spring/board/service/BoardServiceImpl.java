package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDAO;
import com.spring.board.vo.BoardVO;
import com.spring.board.vo.PagingCriteria;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardVO> getBoardList(PagingCriteria paging) {
		return boardDAO.getBoardList(paging);
	}

	@Override
	public BoardVO getContent(BoardVO vo) {
		return boardDAO.getContent(vo);
	}

	@Override
	public void insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);
	}

	@Override
	public BoardVO moveModifytBoard(BoardVO vo) {
		return boardDAO.getContent(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}

	@Override
	public int totalCnt() {
		return boardDAO.totalCnt();
	}
}
