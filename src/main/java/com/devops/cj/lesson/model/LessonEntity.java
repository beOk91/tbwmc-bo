package com.devops.cj.lesson.model;

import java.util.Date;

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
	private long lessonSeq;
	private String startTime;
	private String endTime;
	private String yoil;
	private String cycle;
	private String startDate;
	private Date regDate;
	private long userSeq;
	
	private String name;
}
