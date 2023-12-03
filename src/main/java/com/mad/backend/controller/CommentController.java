package com.mad.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mad.backend.model.Comment;
import com.mad.backend.model.User;
import com.mad.backend.model.Museum;
import com.mad.backend.repo.CommentRepository;
import com.mad.backend.repo.UserRepository;
import com.mad.backend.repo.MuseumRepository;

@RestController
// @RequestMapping("/comment") --- [TBD]
public class CommentController {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
    @Autowired
	private MuseumRepository museumRepository;

    @GetMapping("/comments")
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

    @GetMapping("/comments/{museumId}")
	public List<Comment> getAllCommentsByMuseum(@PathVariable("museumId") String id) {
        Museum foundMuseum = museumRepository.findById(id).get();
		List<Comment> museumCommentList = commentRepository.findByMuseum(foundMuseum);

		return museumCommentList;
	}

    @GetMapping("/comments/{ratings}")
	public List<Comment> searchByRatings(@PathVariable("ratings") int ratings) {
		List<Comment> commentByRatingsList = commentRepository.findByRatings(ratings);

		return commentByRatingsList;
	}

	@PostMapping("/comments/save")
	public Comment saveComment(@RequestBody Comment newComment) {         // --- [TBD]

        // ------- if wanna add a payload request class:
        // @RequestBody CommentPayload payload
        // private String userId;
        // private String museumId;
        // private String title;
        // private String content;
        // private int ratings;

        User user = userRepository.findById(payload.getUserId()).get();
        Museum museum = museumRepository.findById(payload.getMuseumId()).get();

		Comment commentToSave = new Comment(payload.getTitle(), payload.getContent(), payload.getRatings(), user, museum);
        commentToSave.setDate(LocalDateTime.now());

        // --------- either one
		Comment commentSaved = commentRepository.save(commentToSave);
		Comment commentSaved = commentRepository.save(newComment);

        System.out.println("=============> " + commentSaved.toString())
		return commentSaved;
	}

    @PutMapping("/comments/{id}")
    public ResponseEntity<String> updateComment(@PathVariable String id, @RequestBody Book updatedComment) {
        if (!commentRepository.findById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
        }

        updatedComment.setId(id); 
        commentRepository.save(updatedComment);

        return ResponseEntity.ok("Comment updated successfully");
    }

    @DeleteMapping("/comments//{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        if (!commentRepository.findById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
        }

        commentRepository.deleteById(id);

        return ResponseEntity.ok("Book deleted successfully");
    }

}
