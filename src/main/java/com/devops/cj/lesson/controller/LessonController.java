package com.devops.cj.lesson.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
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
import com.devops.cj.util.HolidayFilter;

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

	@GetMapping(value = "/test")
	public ResponseEntity<Object> test() throws Exception {
		
		/************************ DayOfWeek TEST ************************/
//		LocalDate date = LocalDate.parse("2023-02-05", DateTimeFormatter.ISO_DATE);
//		DayOfWeek dayOfWeek = date.getDayOfWeek();
//		System.out.println(dayOfWeek);
//
//		int dayOfWeekNumber = dayOfWeek.getValue();
//		System.out.println(dayOfWeekNumber); // 7 일요일
//
//		dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US);
		
		/************************ HolidayFilter TEST ************************/
		long date1 = DateUtils.parseDate("20230505", "yyyyMMdd").getTime(); // 어린이날
		long date2 = DateUtils.parseDate("20230606", "yyyyMMdd").getTime(); // 현충일
		long date3 = DateUtils.parseDate("20230124", "yyyyMMdd").getTime(); // 대체공휴일
		long date4 = DateUtils.parseDate("20230214", "yyyyMMdd").getTime(); // 일반
		
		boolean isLegalHoliday1 = HolidayFilter.isLegalHoliday(date1);
		boolean isWeekend1 = HolidayFilter.isWeekend(date1);
		boolean isAlternative1 = HolidayFilter.isAlternative(date1);
		boolean isHoliday1 = HolidayFilter.isHoliday(date1);
		
		boolean isLegalHoliday2 = HolidayFilter.isLegalHoliday(date2);
		boolean isWeekend2 = HolidayFilter.isWeekend(date2);
		boolean isAlternative2 = HolidayFilter.isAlternative(date2);
		boolean isHoliday2 = HolidayFilter.isHoliday(date2);
		
		boolean isLegalHoliday3 = HolidayFilter.isLegalHoliday(date3);
		boolean isWeekend3 = HolidayFilter.isWeekend(date3);
		boolean isAlternative3 = HolidayFilter.isAlternative(date3);
		boolean isHoliday3 = HolidayFilter.isHoliday(date3);
		
		boolean isLegalHoliday4 = HolidayFilter.isLegalHoliday(date4);
		boolean isWeekend4 = HolidayFilter.isWeekend(date4);
		boolean isAlternative4 = HolidayFilter.isAlternative(date4);
		boolean isHoliday4 = HolidayFilter.isHoliday(date4);


		System.out.println(isLegalHoliday1);
		System.out.println(isWeekend1);
		System.out.println(isAlternative1);
		System.out.println(isHoliday1);
		
		System.out.println(isLegalHoliday2);
		System.out.println(isWeekend2);
		System.out.println(isAlternative2);
		System.out.println(isHoliday2);
		
		System.out.println(isLegalHoliday3);
		System.out.println(isWeekend3);
		System.out.println(isAlternative3);
		System.out.println(isHoliday3);
		
		System.out.println(isLegalHoliday4);
		System.out.println(isWeekend4);
		System.out.println(isAlternative4);
		System.out.println(isHoliday4);

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