package com.timucin.gamelookup.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.timucin.gamelookup.domain.User;

public class AuthenticatedUser extends User implements UserDetails {

	private static final long serialVersionUID = 209706356119253245L;

	protected AuthenticatedUser(User user) {
		super(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getShelves());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
