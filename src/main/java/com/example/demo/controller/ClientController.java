package com.example.demo.controller;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.User;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;
    
    
    @GetMapping("/showAppointments")
    public String createAppointment(Model m) {
    	
    	List<Appointment> listofAppointments =appointmentService.getAll();
    	m.addAttribute("listofAppointments", listofAppointments);
    	
        return "showAppointments"; 
    }

    
    
    @GetMapping("/BookingPage/{id}")
    public String createAppointment(@PathVariable Long id, Model m) {
    	
    	Optional<Appointment> appoint = appointmentService.findById(id);
    	if(appoint.isEmpty() || appoint.get().isBooked())
    	{
    		return "redirect:/client/showAppointments";  //Here we can give invalid id 
    	}
    	appoint.get().setBooked(true);
    	appointmentService.saveAppointment(appoint.get());
    	System.out.println(appoint.get().isBooked());
    	
        return "redirect:/client/showAppointments"; 
    }
    

    
}
