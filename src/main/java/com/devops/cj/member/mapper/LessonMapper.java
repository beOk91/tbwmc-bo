package com.devops.cj.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.devops.cj.member.dto.LessonDto;

@Mapper
public interface LessonMapper {
	public Integer selectLessonSeq();
	public Integer insertLesson(LessonDto lessonDto);
}
