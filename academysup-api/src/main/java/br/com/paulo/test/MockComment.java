package br.com.paulo.test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import br.com.paulo.entities.Comment;
import br.com.paulo.entities.Post;
import br.com.paulo.entities.User;

public class MockComment {

    public Comment mockComment1() {
        return new Comment(1L, new User(), new Post(), "Testando1", Instant.now());
    }

    public Comment mockComment2() {
    	return new Comment(1L, new User(), new Post(), "Testando2", Instant.now());
    }

    public List<Comment> mockCommentList() {
        List<Comment> comments = new ArrayList<>();
        comments.add(mockComment1());
        comments.add(mockComment2());
        return comments;
    }
}
