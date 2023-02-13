package com.devops.cj.lesson.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonVO {
	
	private String cycle;
	private List<Map<String, Object>> fields;
	private String startDate;
	private String userName;
	
	private String dayValue;
}
