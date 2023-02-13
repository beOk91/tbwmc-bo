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
import com.devops.cj.lesson.model.LessonEntity;
import com.devops.cj.lesson.model.LessonVO;

@Service
public class LessonService {

	private static final Log logger = LogFactory.getLog(LessonService.class);

	@Autowired
	private LessonMapper1 lessonMapper1;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertLesson(LessonVO lessonVO) throws Exception {
		LessonEntity lessonEntity = new LessonEntity();

		lessonEntity.setUserSeq(4); // 테스트를 위한 고정값 지정
		lessonEntity.setCycle(lessonVO.getCycle());
		lessonEntity.setStartDate(lessonVO.getStartDate());

		// fields 값을 추출하기 위한 로직 추가
		if (lessonVO.getFields().size() > 0) {
			for (int i = 0; i < lessonVO.getFields().size(); i++) {
				HashMap<String, Object> fieldsMap = new HashMap<String, Object>(lessonVO.getFields().get(i));
				lessonEntity.setYoil(fieldsMap.get("yoil").toString());

				// 시작일자와 요일 정보 세팅 후 dayOfTheWeekSetting 호출 (return : startDate)
				String startDate = dayOfTheWeekSetting(lessonEntity);
				
				// cycle 정보에 따른 startDate 세팅
				LocalDate cycleDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
				int cycleCnt = Integer.parseInt(lessonEntity.getCycle()) * 4; // 반복주기 count
				for(int j = 0; j < cycleCnt; j++) {
					
					List<Map> rangeInfo = (List<Map>) fieldsMap.get("rangeTime");
					lessonEntity.setStartTime(String.valueOf(rangeInfo.get(0)).replaceAll(":", ""));
					lessonEntity.setEndTime(String.valueOf(rangeInfo.get(1)).replaceAll(":", ""));
					
					lessonEntity.setStartDate(cycleDate.toString().replaceAll("-", ""));
					logger.info("lessonEntity : " + lessonEntity.toString());
					lessonMapper1.insertLesson1(lessonEntity);
					
					cycleDate = cycleDate.plusDays(7); // 시작일자+7 세팅
					
				}
			}
		}
	}

	/* 시작일자와 선택 요일에 따른 일정 세팅 */
	public String dayOfTheWeekSetting(LessonEntity lessonEntity) {

		String startDate = lessonEntity.getStartDate();
		LocalDate date = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
		
		// 시작일자의 요일을 반환
		DayOfWeek startDateYoil = date.getDayOfWeek();
		logger.info("startDateYoil : " + startDateYoil); // MONDAY ... SUNDAY
		
		// 선택된 요일의 날짜를 반환
		DayOfWeek selectedYoil = null;
		if ("MON".equals(lessonEntity.getYoil())) {
			selectedYoil = DayOfWeek.MONDAY;
		} else if ("TUE".equals(lessonEntity.getYoil())) {
			selectedYoil = DayOfWeek.TUESDAY;
		} else if ("WEN".equals(lessonEntity.getYoil())) {
			selectedYoil = DayOfWeek.WEDNESDAY;
		} else if ("THU".equals(lessonEntity.getYoil())) {
			selectedYoil = DayOfWeek.THURSDAY;
		} else if ("FRI".equals(lessonEntity.getYoil())) {
			selectedYoil = DayOfWeek.FRIDAY;
		} else if ("SAT".equals(lessonEntity.getYoil())) {
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
