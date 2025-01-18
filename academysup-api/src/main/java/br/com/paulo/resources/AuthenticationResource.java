package br.com.paulo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.dto.AuthenticationDTO;
import br.com.paulo.dto.RegisterDTO;
import br.com.paulo.entities.Permission;
import br.com.paulo.entities.User;
import br.com.paulo.entities.UserPermission;
import br.com.paulo.repositories.PermissionRepository;
import br.com.paulo.repositories.UserPermissionRepository;
import br.com.paulo.repositories.UserRepository;
import br.com.paulo.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthenticationResource {
	
	@Autowired JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserPermissionRepository userPermissionRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;

	@PostMapping("/login")
	@Operation(summary = "Login user", description = "Login user", tags = {"Login"})
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var user = (User) auth.getPrincipal();
		var token = jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles());
		
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/register")
	@Operation(summary = "Register user", description = "Register user", tags = {"Register"})
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if(this.repository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		
		User newUser = new User();
		updateUser(newUser, data, encryptedPassword);
		
		this.repository.save(newUser);
		
		return ResponseEntity.ok().build();
	}
	
	private User updateUser(User user, RegisterDTO data, String encryptedPassword) {
	    user.setUserName(data.username());
	    user.setEmail(data.email());
	    user.setPassword(encryptedPassword);
	    user.setAccountNonExpired(true);
	    user.setAccountNonLocked(true);
	    user.setCredentialsNonExpired(true);
	    user.setEnabled(true);

	    List<Permission> roles = new ArrayList<>();

	    for (String role : data.roles()) {
	        Permission permission = permissionRepository.findByDescription(role);
	        
	        if (permission == null) {
	            permission = new Permission();
	            permission.setDescription(role);
	            permission = permissionRepository.save(permission);
	        }
	        
	        roles.add(permission);        
	    }

	    user = repository.save(user);

	    for (Permission role : roles) {
	        UserPermission userPermission = new UserPermission(user, role);
	        userPermissionRepository.save(userPermission);
	    }

	    return user;
	}


}
