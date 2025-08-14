package com.example.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class CustomUserService implements UserDetailsService {
	
	
	 @Autowired
	    private UserService userService;

	    @Override
	    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
	        Optional<User> user = userService.findByEmail(userEmail);
	        
	        if(user.isEmpty()) {
	            throw new UsernameNotFoundException("User not found with email: " + userEmail);
	        }
	        
	        return new CustomUserDetails(user.get());
	    }

}
