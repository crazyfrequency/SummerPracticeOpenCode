package com.summer.practice.model.enums;

import java.util.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.*;

import static com.summer.practice.model.enums.Permissions.*;

@RequiredArgsConstructor
public enum Role {
	ADMIN(Set.of(
		READ_DATA,
		EDIT_DATA,
		CREATE_USER
	)),
	USER(Set.of(
		READ_DATA
	));
	
	@Getter
	private final Set<Permissions> permissions;
	
	public List<SimpleGrantedAuthority> getAuthorities() {
		var authorities = getPermissions()
			.stream()
			.map(p -> new SimpleGrantedAuthority(p.getPermission()))
			.toList();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
		return authorities;
	}
}
