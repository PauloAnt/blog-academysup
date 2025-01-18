package br.com.paulo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.paulo.security.SecurityFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	SecurityFilter securityFilter;
	
	public SecurityConfiguration() {}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity req) throws Exception {
		return req
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/auth/**", 
                                "/swagger-ui/**", 
                                "/swagger-ui.html", 
                                "/v3/api-docs/**", 
                                "/api-docs/**", 
                                "/swagger-resources/**", 
                                "/swagger-resources", 
                                "/swagger-resources/configuration/ui", 
                                "/swagger-resources/configuration/security").permitAll()
						.requestMatchers("/v1/post","/v1/comment", "/v1/post/**", "/v1/comment/**").authenticated())
				
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
