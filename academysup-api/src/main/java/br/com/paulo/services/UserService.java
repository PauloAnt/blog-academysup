package br.com.paulo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.paulo.entities.User;
import br.com.paulo.repositories.UserRepository;


@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Not found Username! " + username);	}
		
	}
	
	public Long getIdUserAuth() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		var user = (User) authentication.getPrincipal();
		
		return user.getId();
	}
	
}
