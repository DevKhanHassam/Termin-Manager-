package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/public")
public class AuthController {

	

	
	
    @Autowired
    private UserService userService;

    
    
    @GetMapping("/login")
    public String loginPage(Model model) {
    	model.addAttribute("user", new User());
        return "login";  
    }
    
    
    
    @PostMapping("/login")
    public String loginPage(@ModelAttribute("user") User user,RedirectAttributes redirectAttributes) 
    {
    	redirectAttributes.addFlashAttribute("user",user);
    	try {
            String page = userService.userAuthenticateByEmail(user);
            if (page.equals("redirect:/public/dashboard")) {
                redirectAttributes.addFlashAttribute("user", user);
            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid credentials");
            }
            return page;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials");
            return "redirect:/public/login";
        }
    }
    
    

    @GetMapping("/dashboard")
    public String showDashBoard(@ModelAttribute("user") User user,Model model) {
    	
        return "dashboard"; 
    }

    
    
   

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
    	
  
        if (userService.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "error.user", "Email already in use");
        }

        if (result.hasErrors()) {
            return "/register";
        }
        user.setRole("CLIENT");
        System.out.print(user.toString());
        redirectAttributes.addFlashAttribute(user);
        userService.saveUser(user);
        return "redirect:/public/dashboard";
        
    }
    
    
    
}
