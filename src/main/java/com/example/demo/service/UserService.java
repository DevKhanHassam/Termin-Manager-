package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import javax.security.sasl.AuthenticationException;

@Service
public class UserService {
	
	@Autowired
	PasswordEncoder E;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User saveUser(User user) {
    	
        user.setPassword(E.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    
   public String userAuthenticateByEmailandPwd(User x)
   
   
   
   {
	   Optional<User> user = userRepository.findByEmail(x.getEmail());
	   if(user.isEmpty())
	   {
		   return "redirect:/public/login";
	   }
	   
	   if(!(user.get().getPassword().equals(x.getPassword())))
	   {
		   return "redirect:/public/login";
	   }
			   
	   else
	   {
		   return "redirect:/public/dashboard";
	   }
   }
}
