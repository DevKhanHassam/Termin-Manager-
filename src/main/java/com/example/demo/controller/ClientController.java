package com.example.demo.controller;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/available")
    public String viewAvailableAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAvailableAppointments());
        return "client/available_appointments"; // templates/client/available_appointments.html
    }

    @PostMapping("/book/{id}")
    public String bookAppointment(@PathVariable Long id, Authentication authentication) {
        String email = authentication.getName();
        appointmentService.bookAppointment(id, email);
        return "redirect:/client/available?booked";
    }
}
