package com.qnit18social.controller;

import com.qnit18social.models.Comment;
import com.qnit18social.models.User;
import com.qnit18social.service.CommentService;
import com.qnit18social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization")String jwt,
                                 @PathVariable("postId") Integer postId) throws Exception {
        User user = userService.findUserByJwt(jwt);

        Comment newComment = commentService.createComment(comment, postId, user.getId());

        return newComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization")String jwt,
                                 @PathVariable("commentId") Integer commentId) throws Exception {
        User user = userService.findUserByJwt(jwt);

        Comment likeComment = commentService.likeComment(commentId, user.getId());

        return likeComment;
    }

    @GetMapping("/api/findcomment/{commentId}")
    public Comment findCommentById(@RequestHeader("Authorization")String jwt,
                               @PathVariable("commentId") Integer commentId) throws Exception {
        User user = userService.findUserByJwt(jwt);

        return commentService.findCommentById(commentId);
    }
}
