package com.blog.service;

import java.util.List;

import com.blog.entity.Post;
import com.blog.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto dto, Integer userId, Integer categoryId);

	Post updatePost(PostDto dto, Integer postId);

	Post deletePost(Integer postId);

	List<Post> getAllPost();

	Post getPostById(Integer postId);

	List<Post> getPostByCategory(Integer categoryId);

	List<Post> getPostByUser(Integer userId);

	List<Post> searchPost(String keyword);

}
