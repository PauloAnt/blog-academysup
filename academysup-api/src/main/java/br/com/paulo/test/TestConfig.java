package br.com.paulo.test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.paulo.entities.Comment;
import br.com.paulo.entities.Post;
import br.com.paulo.entities.User;
import br.com.paulo.repositories.CommentRepository;
import br.com.paulo.repositories.PostRepository;
import br.com.paulo.repositories.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {

        // Busca ou cria usuários
        User u1 = userRepository.findByUsername("julia");
        if (u1 == null) {
            u1 = new User(null, "julia", "julia@example.com", "password", true, true, true, true, new ArrayList<>());
            userRepository.save(u1);
        }

        User u2 = userRepository.findByUsername("paulo");
        if (u2 == null) {
            u2 = new User(null, "paulo", "paulo@example.com", "password", true, true, true, true, new ArrayList<>());
            userRepository.save(u2);
        }

        // Criação de posts se a tabela estiver vazia
        if (postRepository.findAll().isEmpty()) {
            Post p1 = new Post(null, u1, Instant.now(), "Programação Java", "Quero compartilhar que estou aprendendo sobre JUnit para testar as minhas APIs.");
            Post p2 = new Post(null, u1, Instant.now(), "Programação Python", "Estou aprendendo sobre SKlearn para treinar IAs.");

            postRepository.saveAll(Arrays.asList(p1, p2));

            // Criação de comentários se a tabela estiver vazia
            if (commentRepository.findAll().isEmpty()) {
                Comment c1 = new Comment(null, u2, p1, "Muito legal, continue assim!", Instant.now());
                commentRepository.save(c1);
            }
        }
    }
}
