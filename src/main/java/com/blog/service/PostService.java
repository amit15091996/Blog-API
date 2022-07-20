package com.blog.service;

import java.util.List;

import com.blog.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto dto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto dto, Integer postId);

	void deletePost(Integer postId);

	List<PostDto> getAllPost();

	PostDto getPostById(Integer postId);

	List<PostDto> getPostByCategory(Integer categoryId);

	List<PostDto> getPostByUser(Integer userId);

	List<PostDto> searchPost(String keyword);

}
