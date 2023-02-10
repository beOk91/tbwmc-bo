package com.devops.cj.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class LessonDto {
	String lessonSeq;
	String lessonDay;
	String lessonTime;
	String lessonStartDate;
	String monthOfRegistration;
	
	String memberSeq;
}
