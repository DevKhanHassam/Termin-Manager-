package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    public String showAppointmentsForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "admin/create_appointment"; // templates/admin/create_appointment.html
    }

    @PostMapping("/appointments")
    public String createAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/admin/appointments?created";
    }
}
