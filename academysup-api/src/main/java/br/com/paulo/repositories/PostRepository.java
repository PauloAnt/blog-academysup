package br.com.paulo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.paulo.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	boolean existsById(Long id);
	
	@Query("SELECT p FROM Post p WHERE p.creator =: creator")
	boolean findByUserId(@Param("creator") Long id);
}
