package com.devops.cj.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.devops.cj.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public List<MemberDto> selectMember(MemberDto memberDto);
	public Integer insertMember(MemberDto memberDto);
	public Integer updateMember(MemberDto memberDto);

}
