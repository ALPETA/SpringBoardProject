package com.spring.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.vo.BoardVO;
import com.spring.board.vo.PagingCriteria;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertBoard(BoardVO vo) {
		System.out.println("insert");
		mybatis.insert("BoardMapper.insertBoard", vo);
	}

	public void updateBoard(BoardVO vo) {
		System.out.println("update");
		mybatis.update("BoardMapper.updateBoard", vo);
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("delete");
		mybatis.delete("BoardMapper.deleteBoard", vo);
	}

	public BoardVO getContent(BoardVO vo) {
		System.out.println("getContent and upHit");
		mybatis.update("BoardMapper.upHit", vo);
		return (BoardVO) mybatis.selectOne("BoardMapper.getContent", vo);
		
	}

	public List<BoardVO> getBoardList(PagingCriteria paging) {
		System.out.println("list");
		return mybatis.selectList("BoardMapper.getBoardList",paging);
	}
	
	public int totalCnt() {
		System.out.println("cnt");
		return mybatis.selectOne("BoardMapper.getTotalCnt");
	}
}
