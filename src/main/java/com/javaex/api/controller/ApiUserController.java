package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/api/user")
public class ApiUserController {

	@Autowired
	private UserService userService;

	// ajax id검사

	@ResponseBody
	@RequestMapping(value = "/idCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public int idCheck(@RequestParam("id") String id) {

		System.out.println("[ApiGuestbookController.write()]");

		System.out.println(id);

		return userService.idCheck(id);

	}
	
	@ResponseBody
	@RequestMapping(value = "/join2", method = { RequestMethod.GET, RequestMethod.POST })
	public int join2(@RequestBody UserVo userVo) {

		System.out.println("[UserController.join2()]");
		
		System.out.println(userVo);
		
		int count = userService.joinUser(userVo);

		return count;
	}

}
