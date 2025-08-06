package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    public String createAppointment(Model m) {
    	m.addAttribute("appoint", new Appointment());
        return "AppointmentForm"; 
    }
    
    
    

    @PostMapping("/appointments")
    public String createAppointment(@ModelAttribute("appoint") Appointment appoint, RedirectAttributes redirectAttributes) {
    	
    	Appointment  a = appointmentService.saveAppointment(appoint);
    	redirectAttributes.addFlashAttribute("a", a);
        return "redirect:/admin/addedPointMent";
    }
    
    
    @GetMapping("/addedPointMent")
    public String addedAppointment(Model m) {
    	
        return "AddedPointMent"; 
    }
    
}
