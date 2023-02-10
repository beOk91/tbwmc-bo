package com.devops.cj.member.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter @Getter
public class MemberDto {
	String memberSeq;
	String memberName;
	String email;
	String registeredDt;
	String phoneNumber;
	
	String lessonStartDate;
	
	List<LessonDto> dataSource;
	
	String lessonDay;
	String lessonTime;

	String lessonEndDate;
	String monthOfRegistration;
}
