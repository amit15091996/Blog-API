package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repo.CategoryRepo;
import com.blog.repo.PostRepo;
import com.blog.repo.UserRepo;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImgName("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto dto, Integer postId) {

		return null;
	}

	@Override
	public Post deletePost(Integer postId) {

		return null;
	}

	@Override
	public List<Post> getAllPost() {

		return null;
	}

	@Override
	public Post getPostById(Integer postId) {

		return null;
	}

	@Override
	public List<Post> getPostByCategory(Integer categoryId) {

		return null;
	}

	@Override
	public List<Post> getPostByUser(Integer userId) {

		return null;
	}

	@Override
	public List<Post> searchPost(String keyword) {

		return null;
	}

}
