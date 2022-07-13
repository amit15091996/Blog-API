package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payload.UserDTO;

@Service
public interface UserService {

	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer userId);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer userId);
 }
