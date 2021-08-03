package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// 게시판 리스트
	public List<BoardVo> getList(String keyword) {
		System.out.println("[BoardService.getList()]");

		List<BoardVo> boardList = boardDao.selectList(keyword);

		return boardList;
	}

	
	// 게시글 삭제
	public int removeBoard(int no) {
		System.out.println("[BoardService.removeBoard()]");

		int count = boardDao.deleteBoard(no);

		return count;
	}

	
	// 글 가져오기(읽기)
	public BoardVo getBoardRead(int no) {
		System.out.println("[BoardService.getBoard()]");

		// 조회수 올리기
		int count = boardDao.updateHit(no);

		// 게시판 정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);

		return boardVo;
	}

	
	// 글 가져오기(수정폼)
	public BoardVo getBoardModifyForm(int no) {
		System.out.println("[BoardService.getBoard()]");

		// 게시판 정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);

		return boardVo;
	}

	
	// 글수정
	public int modifyBoard(BoardVo boardVo) {
		System.out.println("[BoardService.modifyBoard()]");

		return boardDao.updateBoard(boardVo);
	}

	
	// 글쓰기
	public int writeBoard(BoardVo boardVo) {
		System.out.println("[BoardService.addBoard()]");

		return boardDao.insertBoard(boardVo);
	}

}
