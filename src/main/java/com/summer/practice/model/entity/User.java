package com.summer.practice.model.entity;

import java.util.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;

import com.summer.practice.model.enums.Role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	@Column
	private String password;

	@CreationTimestamp
	private Date createdAt;

	@Enumerated(EnumType.ORDINAL)
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}

	

}
