package com.devops.cj.lesson.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.devops.cj.lesson.model.LessonEntity;

@Mapper
@Repository
public interface LessonMapper {
	public void insertLesson1(LessonEntity lessonEntity) throws Exception;
	public List<LessonEntity> selectDayScheduleList() throws Exception;
}
