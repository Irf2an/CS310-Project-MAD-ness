package com.mad.backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mad.backend.model.*;
import com.mad.backend.repo.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MuseumRepository museumRepository;

    @GetMapping("/allComments")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // ---
    @GetMapping("/{museumId}")
    public List<Comment> getAllCommentsByMuseum(@PathVariable("museumId") String id) {
        Museum foundMuseum = museumRepository.findById(id).get();
        return commentRepository.findByMuseum(foundMuseum);
    }

    // ---
    @GetMapping("/{ratings}")
    public List<Comment> searchByRatings(@PathVariable("ratings") int ratings) {
        return commentRepository.findByRatings(ratings);
    }

    @PostMapping("/save")
    public ResponseEntity<MyResponse> saveComment(@RequestBody Comment newComment) {
        newComment.setDate(LocalDateTime.now());
        Comment commentSaved = commentRepository.save(newComment);

        MyResponse response = new MyResponse();
        response.setMessage("Comment successfully created!");
        response.setData(commentSaved);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MyResponse> updateComment(@PathVariable String id, @RequestBody Comment updatedComment) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();

            // Update fields with non-null values from updatedMuseum
            if (updatedComment.getTitle() != null) {
                existingComment.setTitle(updatedComment.getTitle());
            }
            if (updatedComment.getContent() != null) {
                existingComment.setContent(updatedComment.getContent());
            }
            if (updatedComment.getRatings() != 0) {
                existingComment.setRatings(updatedComment.getRatings());
            }
            if (updatedComment.getUser() != null) {
                existingComment.setUser(updatedComment.getUser());
            }
            if (updatedComment.getMuseum() != null) {
                existingComment.setMuseum(updatedComment.getMuseum());
            }
            if (updatedComment.getDate() != null) {
                existingComment.setDate(updatedComment.getDate());
            }

            Comment updated = commentRepository.save(existingComment);
            MyResponse response = new MyResponse();
            response.setMessage("Comment with ID: " + id + " has been successfully updated!");
            response.setData(updated);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            MyResponse response = new MyResponse();
            response.setMessage("Comment with ID: " + id + " not found!");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MyResponse> deleteComment(@PathVariable String id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            museumRepository.deleteById(id);

            MyResponse response = new MyResponse();
            response.setMessage("Comment with ID " + id + " has been deleted!");

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            MyResponse response = new MyResponse();
            response.setMessage("Comment with ID " + id + " not found!");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
