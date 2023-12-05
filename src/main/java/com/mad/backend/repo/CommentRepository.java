package com.mad.backend.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mad.backend.model.Comment;
//import com.mad.backend.model.User;
import com.mad.backend.model.Museum;

public interface CommentRepository extends MongoRepository<Comment, String> {

	//public List<Comment> findByUser(User user);

	public List<Comment> findByMuseum(Museum museum);

    public List<Comment> findByRatings(int ratings);

}