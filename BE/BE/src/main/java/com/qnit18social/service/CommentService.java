package com.qnit18social.service;

import com.qnit18social.models.Comment;

public interface CommentService {

    Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;

    Comment findCommentById(Integer commentId) throws Exception;

    Comment likeComment(Integer commentId, Integer userId) throws Exception;
}
