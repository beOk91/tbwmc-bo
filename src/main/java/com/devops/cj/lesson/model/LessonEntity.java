package com.devops.cj.lesson.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LessonEntity {
	/** LESSON TABLE COLUMN */
	private long lessonSeq;
	private String lessonDay;
	private String startTime;
	private String endTime;
	private String lessonStartDate;
	private String monthOfRegistration;
	private long memberSeq;
	
	// 기타 필요한 정보
	private long lessonDetailSeq;
	private String lessonDate;
	private String name;
}
