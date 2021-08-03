package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 게시글 전체 가져오기
	public List<BoardVo> selectList(String keyword) {
		System.out.println("[BoardDao.selectList()]");

		return sqlSession.selectList("board.selectList", keyword);
	}

	
	// 게시판 글 삭제
	public int deleteBoard(int no) {
		System.out.println("[BoardDao.deleteBoard()]");

		return sqlSession.delete("board.deleteBoard", no);
	}
	
	
	// 조회수 올리기(글읽기)
	public int updateHit(int no) {
		System.out.println("[BoardDao.updateHit()]");

		return sqlSession.update("board.updateHit", no);
	}
	

	// 게시판1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("[BoardDao.selectBoard()]");

		return sqlSession.selectOne("board.selectBoard", no);
	}


	// 글수정
	public int updateBoard(BoardVo boardVo) {
		System.out.println("[BoardDao.updateBoard()]");
		System.out.println(boardVo);
		return sqlSession.update("board.updateBoard", boardVo);
	}

	
	// 글저장
	public int insertBoard(BoardVo boardVo) {
		System.out.println("[BoardDao.insertBoard()]");

		return sqlSession.insert("board.insertBoard", boardVo);
	}
	

}
