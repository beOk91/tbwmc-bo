package com.devops.cj.lesson.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.cj.lesson.dao.LessonMapper1;
import com.devops.cj.lesson.model.LessonDetailEntity;
import com.devops.cj.lesson.model.LessonEntity;
import com.devops.cj.lesson.model.LessonVO;

@Service
public class LessonService {

	private static final Log logger = LogFactory.getLog(LessonService.class);

	@Autowired
	private LessonMapper1 lessonMapper1;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertLesson(LessonVO lessonVO) throws Exception {
		
		// fields 값을 세팅한다.
		if (lessonVO.getFields().size() > 0) {
			for (int i = 0; i < lessonVO.getFields().size(); i++) {
				LessonEntity lessonEntity = new LessonEntity();
				
				int lessonSeq = lessonMapper1.selectLessonSeq();
				
				lessonEntity.setLessonSeq(lessonSeq);
				lessonEntity.setMemberSeq(4); // 테스트를 위한 고정값 지정
				lessonEntity.setMonthOfRegistration(lessonVO.getCycle());
				lessonEntity.setLessonStartDate(lessonVO.getStartDate());
				
				HashMap<String, Object> fieldsMap = new HashMap<String, Object>(lessonVO.getFields().get(i));
				lessonEntity.setLessonDay(fieldsMap.get("yoil").toString());
				
				List<Map> rangeInfo = (List<Map>) fieldsMap.get("rangeTime");
				lessonEntity.setStartTime(String.valueOf(rangeInfo.get(0)).replaceAll(":", "")); // 시작시간
				lessonEntity.setEndTime(String.valueOf(rangeInfo.get(1)).replaceAll(":", ""));  // 종료시간
				
				/** LESSON TABLE INSERT */
				logger.info("lessonEntity : " + lessonEntity.toString());
				lessonMapper1.insertLesson1(lessonEntity);
				
				// 시작일자 기준으로 field의 요일 정보를 반환한다.
				String startDate = dayOfTheWeekSetting(lessonEntity);
				
				// cycle 정보에 따른 startDate 세팅한다.
				LocalDate cycleDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
				int cycleCnt = Integer.parseInt(lessonEntity.getMonthOfRegistration()) * 4; // 반복주기 count
				
				for(int j = 0; j < cycleCnt; j++) {
					LessonDetailEntity lessonDetailEntity = new LessonDetailEntity();
					
					lessonDetailEntity.setLessonDate(cycleDate.toString() + " " + String.valueOf(rangeInfo.get(0)));
					lessonDetailEntity.setLessonYN(0); // 미수강 : 0, 수강 : 1
					lessonDetailEntity.setLessonSeq(lessonSeq);
					
					logger.info("lessonDetailEntity : " + lessonDetailEntity.toString());
					lessonMapper1.insertLessonDetail1(lessonDetailEntity);
					
					// 1. lesson, lesson_datail 로직 변경(O)
					
					// 2. holiday 적용(휴일 로직 재검토)
					// 3. 일정 상세정보 수정하는 로직 추가
					cycleDate = cycleDate.plusDays(7); // 시작일자+7 세팅
				}
			}
		}
		
	}

	/* 시작일자와 선택 요일에 따른 일정 세팅 */
	public String dayOfTheWeekSetting(LessonEntity lessonEntity) {

		String startDate = lessonEntity.getLessonStartDate().toString();
		LocalDate date = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		
		// 시작일자의 요일을 반환
		DayOfWeek startDateYoil = date.getDayOfWeek();
		logger.info("startDateYoil : " + startDateYoil); // MONDAY ... SUNDAY
		
		// 선택된 요일의 날짜를 반환
		DayOfWeek selectedYoil = null;
		if ("MON".equals(lessonEntity.getLessonDay())) {
			selectedYoil = DayOfWeek.MONDAY;
		} else if ("TUE".equals(lessonEntity.getLessonDay())) {
			selectedYoil = DayOfWeek.TUESDAY;
		} else if ("WEN".equals(lessonEntity.getLessonDay())) {
			selectedYoil = DayOfWeek.WEDNESDAY;
		} else if ("THU".equals(lessonEntity.getLessonDay())) {
			selectedYoil = DayOfWeek.THURSDAY;
		} else if ("FRI".equals(lessonEntity.getLessonDay())) {
			selectedYoil = DayOfWeek.FRIDAY;
		} else if ("SAT".equals(lessonEntity.getLessonDay())) {
			selectedYoil = DayOfWeek.SATURDAY;
		} else {
			selectedYoil = DayOfWeek.SUNDAY;
		}
		
		logger.info("selectedYoil : " + selectedYoil);
		return date.with(TemporalAdjusters.nextOrSame(selectedYoil)).toString();
	}

	public List<LessonEntity> getDayScheduleList() throws Exception {
		List<LessonEntity> getDayList = lessonMapper1.selectDayScheduleList();
		return getDayList;
	}
}
