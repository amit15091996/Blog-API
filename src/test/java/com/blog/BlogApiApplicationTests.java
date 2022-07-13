package com.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.repo.UserRepo;

@SpringBootTest
class BlogApiApplicationTests {

	@Autowired
	private UserRepo repo;


	@Test
	 void repoTest() {

		String className = this.repo.getClass().getName();
		String packageName = this.repo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);

		String str="";
		assertEquals("",str);
	}

}
