package br.com.paulo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.paulo.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	boolean existsById(Long id);
}
