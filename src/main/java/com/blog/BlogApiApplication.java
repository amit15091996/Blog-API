package com.blog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.entity.Role;
import com.blog.repo.RoleRepo;
import com.blog.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class BlogApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		log.info(this.passwordEncoder.encode("amit123"));

		try {
			Role role1 = new Role();
			role1.setId(Constants.ADMIN_USER);
			role1.setName("ROLE_ADMIN");

			Role role2 = new Role();
			role2.setId(Constants.NORMAL_USER);
			role2.setName("ROLE_NORMAL");

			List<Role> roles = new ArrayList<>(Arrays.asList(role1, role2));
			
			List<Role> result = this.roleRepo.saveAll(roles);
			
			result.forEach(r->{
				log.info(r.getName());
			});

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
