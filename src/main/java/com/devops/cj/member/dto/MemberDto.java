package com.devops.cj.member.dto;

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
	
	String lessonDay;
	String lessonTime;
	String lessonStartDate;
	String lessonEndDate;
	String monthOfRegistration;
}
