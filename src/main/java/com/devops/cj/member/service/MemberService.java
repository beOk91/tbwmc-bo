package com.devops.cj.member.service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devops.cj.member.dto.LessonDetailDto;
import com.devops.cj.member.dto.LessonDto;
import com.devops.cj.member.dto.MemberDto;
import com.devops.cj.member.mapper.LessonDetailMapper;
import com.devops.cj.member.mapper.LessonMapper;
import com.devops.cj.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	LessonMapper lessonMapper;
	@Autowired
	LessonDetailMapper lessonDetailMapper;
	
	public List<MemberDto> retrieveMember(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return memberMapper.selectMember(memberDto);
	}
	
	@Transactional
	public Integer registerMember(MemberDto memberDto) {
		// TODO Auto-generated method stub
		if(null == memberDto.getMemberSeq()) {
			int memberSeq = memberMapper.selectMemberSeq();
			memberDto.setMemberSeq(memberSeq+"");
		}
		
		memberMapper.insertMember(memberDto);
		for(LessonDto dto: memberDto.getDataSource()) {
			if(null == dto.getLessonSeq()) {
				int lessonSeq = lessonMapper.selectLessonSeq();
				dto.setLessonSeq(lessonSeq+"");
			}
			dto.setMemberSeq(memberDto.getMemberSeq());
			lessonMapper.insertLesson(dto);
		}
		
		int cnt = Integer.parseInt(memberDto.getMonthOfRegistration())*memberDto.getDataSource().size()*4;
		
		int year = Integer.parseInt(memberDto.getLessonStartDate().substring(0, 4));
		int month = Integer.parseInt(memberDto.getLessonStartDate().substring(4, 6));
		int day = Integer.parseInt(memberDto.getLessonStartDate().substring(6, 8));
		LocalDate startDt = LocalDate.of(year,month, day);
		while(cnt!=0) {
			for(LessonDto dto: memberDto.getDataSource()) {
				String myDate = (startDt.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase());
				if(dto.getLessonDay().toUpperCase().equals(myDate)) {
					LessonDetailDto lessonDetailDto = new LessonDetailDto();
					lessonDetailDto.setLessonDate(startDt.toString().replaceAll("-", "")+dto.getLessonTime()+"00");
					lessonDetailDto.setLessonSeq(dto.getLessonSeq());
					lessonDetailMapper.insertLessonDetail(lessonDetailDto);
					cnt--;
				}
			}
			startDt=startDt.plusDays(1);
		}
		
		return 1;
		
	}

}
