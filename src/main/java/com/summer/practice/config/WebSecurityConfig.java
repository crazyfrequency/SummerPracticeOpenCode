package com.summer.practice.config;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, Environment env) throws Exception {
		boolean dev = env.acceptsProfiles(Profiles.of("dev"));
		System.out.println(dev);
		for (String profile : env.getActiveProfiles()) {
			System.out.println(profile);
		}
		if (dev) {
			http.cors(cors -> cors.configurationSource(request -> {
				var corsConfiguration = new CorsConfiguration();
				corsConfiguration.setAllowedOriginPatterns(List.of("*"));
				corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
				corsConfiguration.setAllowedHeaders(List.of("*"));
				corsConfiguration.setAllowCredentials(true);
				return corsConfiguration;
			}));
		}
		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(request -> {
				request.anyRequest().permitAll();
			});
		return http.build();
	}

}
