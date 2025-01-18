package br.com.paulo.dto;

import java.time.Instant;

public record PostDTO(Long id, Long userId, String title, String description, Instant moment) {

}
