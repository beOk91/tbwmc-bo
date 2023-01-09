package com.devops.cj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

	/**
	 * TEST 화면
	 * @param
	 * @return
	 */
	@GetMapping(value = "/hello")
	public String test() {
		return "success";
	}
}
