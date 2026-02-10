package com.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				// Disable CSRF
				.csrf(csrf -> csrf.disable())

				// State less session (JWT)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Authorization rules
				.authorizeHttpRequests(auth -> auth

						.requestMatchers("/api/auth/**").permitAll()
						
						// Borrow APIs allowed for USER + ADMIN
						.requestMatchers("/api/borrow/**").hasAnyRole("USER", "ADMIN")

						// GET allowed for USER + ADMIN
						.requestMatchers(HttpMethod.GET, "/api/books/**").hasAnyRole("USER", "ADMIN")

						// POST only ADMIN
						.requestMatchers(HttpMethod.POST, "/api/books/**").hasRole("ADMIN")

						// PUT only ADMIN
						.requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN")

						// DELETE only ADMIN
						.requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")
						
						

						.anyRequest().authenticated())

				// Disable default login
				.formLogin(form -> form.disable())

				// Disable Basic Auth
				.httpBasic(basic -> basic.disable());

		// Add JWT filter
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
