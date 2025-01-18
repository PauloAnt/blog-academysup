package br.com.paulo.test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import br.com.paulo.entities.Post;
import br.com.paulo.entities.User;

public class MockPost {

    public Post mockPost1() {
        return new Post(1L, new User(), Instant.now(), "Teste1", "Testando1");
    }

    public Post mockPost2() {
    	return new Post(1L, new User(), Instant.now(), "Teste2", "Testando2");
    }

    public List<Post> mockPostList() {
        List<Post> posts = new ArrayList<>();
        posts.add(mockPost1());
        posts.add(mockPost2());
        return posts;
    }
}
