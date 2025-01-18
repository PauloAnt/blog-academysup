package br.com.paulo.dto;

import java.util.List;

public record RegisterDTO(String email, String username, String password, List<String> roles) {

}
