package com.devops.cj.member.domain;

import lombok.Data;

@Data
public class Member {
	String memberSeq;
	String memberName;
	String email;
	String registeredDt;
	String monthOfRegistration;
	String phoneNumber;
	
	String lessonDay;
	String lessonStartDate;
	String lessonEndDate;

}
