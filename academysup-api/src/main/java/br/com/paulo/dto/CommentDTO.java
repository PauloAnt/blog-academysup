package br.com.paulo.dto;

import java.time.Instant;

public record CommentDTO(Long id, Long userId, Long postId, String description, Instant moment) {

}
