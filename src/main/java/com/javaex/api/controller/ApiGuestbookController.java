package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/api/guestbook")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	// ajax 리스트 가져오기
	
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestbookVo> list() {

		System.out.println("[ApiGuestbookController.list()]");

		List<GuestbookVo> guestbookList = guestbookService.getGuestList();

		System.out.println(guestbookList);

		return guestbookList;
	}
	
	//ajax 방명록 저장
	
	@ResponseBody
	@RequestMapping(value="/write", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("[ApiGuestbookController.write()]");
		
		System.out.println(guestbookVo);
		
		GuestbookVo resultVo = guestbookService.writeResultVo(guestbookVo);
		
		return resultVo;
		
	}
	
	//ajax 방명록 삭제
	@ResponseBody
	@RequestMapping(value="/remove", method = { RequestMethod.GET, RequestMethod.POST })
	public int remove(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("[ApiGuestbookController.remove()]");
		
		int count = guestbookService.removeGuest(guestbookVo);
		
		System.out.println(count + "건이 삭제되었습니다.");
		
		return count;
	}
	
	
	//ajax 방명록 저장2
	
	@ResponseBody
	@RequestMapping(value="/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {
		
		System.out.println("[ApiGuestbookController.write2()]");
		
		System.out.println(guestbookVo);
		
		GuestbookVo resultVo = guestbookService.writeResultVo(guestbookVo);
		
		return resultVo;
		
	}
	
	//안드로이드 방명록 1개 가져오기
	@ResponseBody
	@RequestMapping(value="/read", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestbookVo read(@RequestBody GuestbookVo guestbookVo) {
		
		System.out.println("[ApiGuestbookController.read()]");
		
		System.out.println(guestbookVo);
		
		int no = guestbookVo.getNo();
		
		System.out.println(no);
		
		GuestbookVo resultVo = guestbookService.readGuestbook(no);
		
		System.out.println("resultVo" + resultVo);
		
		return resultVo;
	}
	
}
