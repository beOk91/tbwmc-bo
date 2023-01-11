package com.devops.cj.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.cj.user.dao.UserMapper;
import com.devops.cj.user.model.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<UserDTO> selectUserList() throws Exception {
		return userMapper.selectUserList();
	}
}
