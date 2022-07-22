package com.blog.service;

import com.blog.payload.CommentDto;

public interface CommentService {

	 CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);
}
