package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//게시판 페이징 연습용 리스트
	
	public Map<String, Object> getList2(int crtPage, String keyword) {
		
		System.out.println("[BoardService.getList2()]");
		
		System.out.println(crtPage);
		
		//////////////////////////////////////
		//리스트가져오기//
		/////////////////////////////////////
		
		int listCnt = 10;
		
		//crtPage 계산 (음수값일때 1페이지로)
		
		/*
		if(crtPage > 0) {
			//crtPage = crtPage;
		} else {
			crtPage = 1;
		}
		*/
		
		
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1);		
		
		//시작번호 계산하기
		
		int startRnum = (crtPage-1)*listCnt+1;
		
		//끝번호 계산하기
		
		int endRnum = (startRnum+listCnt)-1;
		
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum, keyword);
		
		
		//페이징 계산
		
		//전체글 갯수
		int totalCount = boardDao.selectTotalCnt(keyword);
		System.out.println(totalCount);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		// 1 -- > 1~5
		// 2 -- > 1~5
		
		// 6 -- > 6~10
		// 10 --> 6~10
		
		int endPageBtnNo = (int)Math.ceil((crtPage/(double)pageBtnCount))*pageBtnCount;
					//올림해준다	 
		
		//시작 버튼 번호
		
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		//다음 화살표 표현 유무
		
		boolean next = false;
		if((endPageBtnNo * listCnt) < totalCount ) {
			
			next = true;
			
		} else {
			
			endPageBtnNo = (int)Math.ceil( totalCount/(double)listCnt );
			//다음 화살표 버튼이 없을떄 endPageBtnNo를 다시 계산 한다.
			
		}
		
		//이전 화살표 표현 유무
		
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		//리턴하기
		
		Map<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("prev", prev);
		listMap.put("startPageBtnNo", startPageBtnNo);
		listMap.put("endPageBtnNo", endPageBtnNo);
		listMap.put("next", next);
		listMap.put("boardList", boardList);
		
		return listMap;
		
	}

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
		
		for(int i=0; i<127; i++) {
			
			boardVo.setTitle(i + "번째 제목입니다.");
			boardVo.setContent(i + "번째 내용입니다.");
			
			boardDao.insertBoard(boardVo);
			
		}
		
		return 1;
		
		//return boardDao.insertBoard(boardVo);
	}

}
