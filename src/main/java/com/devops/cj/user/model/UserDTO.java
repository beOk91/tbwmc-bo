package com.devops.cj.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	
	private int memberSeq;	 // 회원 key
	private String memberId; // 회원 ID
	private String name;	 // 이름
}
