package com.devops.cj.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.cj.user.model.UserDTO;
import com.devops.cj.user.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
     * 새로운 게시물을 등록한다.
     * @param 
     * @return 
	 * @throws Exception 
     */
    @GetMapping(value = "/list")
    public List<UserDTO> userList(UserDTO UserDTO) throws Exception {
    	List<UserDTO> userList = userService.selectUserList();
        return userList;
    }
}
