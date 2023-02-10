package com.devops.cj.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.devops.cj.member.dto.LessonDetailDto;

@Mapper
public interface LessonDetailMapper {
	public Integer insertLessonDetail(LessonDetailDto lessonDetailDto);
}
