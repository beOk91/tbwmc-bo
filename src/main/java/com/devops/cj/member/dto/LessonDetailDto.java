package com.devops.cj.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class LessonDetailDto {
	String lessonDetailSeq;
	String lessonDate;
	
	String lessonYn;
	String lessonSeq;
	String memberSeq;
}
