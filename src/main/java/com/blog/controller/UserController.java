package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.UserDTO;
import com.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/post")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO createUserDto = this.userService.createUser(userDTO);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId) {

		UserDTO updatedUser = this.userService.updateUser(userDTO, userId);

		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

}
