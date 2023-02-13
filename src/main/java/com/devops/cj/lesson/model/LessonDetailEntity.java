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
public class LessonDetailEntity {
	/** LESSON_DETAIL TABLE COLUMN */
	private long lessonDetailSeq;
	private String lessonDate;
	private int lessonYN;
	private long lessonSeq;
}
