package com.Tareas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SecurityConfig  {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeRequests()
				.antMatchers("/", "/editar", "/eliminar").access("hasRole('USER')")
				.antMatchers("/register", "/login").permitAll().and().formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/")
				// Make H2-Console non-secured; for debug purposes
			      .and()
			        .csrf()
			          .ignoringAntMatchers("/h2-console/**")
			  
			      // Allow pages to be loaded in frames from the same origin; needed for H2-Console
			      .and()  
			        .headers()
			          .frameOptions()
			            .sameOrigin()
			            .and()
			            .csrf()
			            .disable()
			       .build();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
	.passwordEncoder(passwordEncoder());
		
	}

}
