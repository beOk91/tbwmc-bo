package com.devops.cj.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.devops.cj.user.model.UserDTO;

@Mapper
@Repository
public interface UserMapper {
	public List<UserDTO> selectUserList() throws Exception;
}
