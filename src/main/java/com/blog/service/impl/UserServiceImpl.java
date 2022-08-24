package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.UserDTO;
import com.blog.repo.RoleRepo;
import com.blog.repo.UserRepo;
import com.blog.service.UserService;
import com.blog.utils.Constants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDTO createUser(UserDTO userDto) {

		User user = this.dtoToUser(userDto);
		User saveUser = this.userRepo.save(user);
		return this.userToDto(saveUser);

	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.USER, Constants.USER_ID, userId));

		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());

		User updated = this.userRepo.save(user);
		return this.userToDto(updated);
	}
 
	@Override
	public UserDTO getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.USER, Constants.USER_ID, userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {

		List<User> users = this.userRepo.findAll();
		return users.stream().map(this::userToDto).collect(Collectors.toList());

	}

	@Override
	public void deleteUser(Integer userId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.USER, Constants.USER_ID, userId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDTO userDTO) {

		return this.modelMapper.map(userDTO, User.class);

	}

	public UserDTO userToDto(User user) {

		return this.modelMapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);

		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		Role role = this.roleRepo.findById(Constants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDTO.class);
	}
}
