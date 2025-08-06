/*package com.example.demo.security;

import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }



    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();  // or user.getUsername() depending on your User entity
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // implement if you have this logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // implement if you have this logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // implement if you have this logic
    }

    @Override
    public boolean isEnabled() {
        return true; // implement if you have this logic
    }



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
*/