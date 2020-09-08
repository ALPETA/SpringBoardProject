package com.spring.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertBoard(BoardVO vo) {
		mybatis.insert("BoardMapper.insertBoard", vo);
	}

	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardMapper.updateBoard", vo);
	}

	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardMapper.deleteBoard", vo);
	}

	public BoardVO getContent(BoardVO vo) {
		mybatis.update("BoardMapper.upHit", vo);
		return (BoardVO) mybatis.selectOne("BoardMapper.getContent", vo);
		
	}

	public List<BoardVO> getBoardList() {
		return mybatis.selectList("BoardMapper.getBoardList");
	}
}
