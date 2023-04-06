package com.hami.controller;

import com.hami.entity.Comment;
import com.hami.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired private CommentService commentService;

    @PostMapping("/createComment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {

        Comment newComment = commentService.createComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("/findAll/{postId}")
    public ResponseEntity<List<Comment>> getACommentsForPost(@PathVariable("postId") String postId) {

        List<Comment> comments =commentService.getAllComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
