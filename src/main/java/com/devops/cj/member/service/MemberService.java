package com.devops.cj.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.cj.member.dto.MemberDto;
import com.devops.cj.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	
	public List<MemberDto> retrieveMember(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return memberMapper.selectMember(memberDto);
	}

	public Integer registerMember(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return memberMapper.insertMember(memberDto);
	}

}
