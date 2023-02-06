package com.devops.cj.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.cj.member.dto.MemberDto;
import com.devops.cj.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/list")
	public List<MemberDto> retrieveMember(MemberDto memberDto){
		return memberService.retrieveMember(memberDto);
	}
	
	@PostMapping("/register")
	public Integer registerMember(@ModelAttribute MemberDto memberDto) {
		memberService.registerMember(memberDto);
		return null;
	}
}
