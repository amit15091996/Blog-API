package com.blog.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "UserName must be of 4 characters !!")

	private String name;
	@Email(message = "Invalid email address !!")

	private String email;

	@NotEmpty
	@Size(min = 4, max = 8, message = "Password must be between 4 to 8 characters !!")
	@JsonIgnoreProperties("enc")
	private String password;

	@NotNull
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
}
