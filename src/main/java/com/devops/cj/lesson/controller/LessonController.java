package com.devops.cj.lesson.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.cj.lesson.model.LessonEntity;
import com.devops.cj.lesson.model.LessonVO;
import com.devops.cj.lesson.service.LessonService;

@RestController
@RequestMapping(value = "lesson")
public class LessonController {

	private static final Log logger = LogFactory.getLog(LessonController.class);

	@Autowired
	private LessonService lessonService;

	/**
	 * 레슨 수강을 등록한다.
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/insertSchedule")
	public ResponseEntity<Object> insertLesson(@RequestBody LessonVO lessonVO) throws Exception {
		lessonService.insertLesson(lessonVO);
		return ResponseEntity.ok("SUCCESS");
	}

	@PostMapping(value = "/test")
	public ResponseEntity<Object> test() throws Exception {

		LocalDate date = LocalDate.parse("2023-02-05", DateTimeFormatter.ISO_DATE);
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		System.out.println(dayOfWeek);

		int dayOfWeekNumber = dayOfWeek.getValue();
		System.out.println(dayOfWeekNumber); // 7 일요일

		dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US);

		return ResponseEntity.ok("SUCCESS");
	}

	/**
	 * 등록된 수강정보를 조회한다.(캘린더의 상세 스케줄)
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getDayScheduleList")
	public List<LessonEntity> getDayScheduleList() throws Exception {
		List<LessonEntity> getDayList = lessonService.getDayScheduleList();
		logger.info("List<LessonEntity> getDayList : " + getDayList.toString());
		return getDayList;
	}
}