package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.User;
import com.example.demo.repository.AppointmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

	
	@Autowired
	UserService userService;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    
    
    
    public List<Appointment> getAll() {
    return appointmentRepository.findAll();
    	}
   

    public List<Appointment> getAppointmentsByUser(User user) {
        return appointmentRepository.findByUser(user);
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

   

    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }
    
    public boolean bookAppointment(Long appointmentId, String email) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();

            if (appointment.isBooked()) {
                return false; // Already booked
            }

            // Get the user by email
            Optional<User> userOptional = userService.findByEmail(email);
            if (userOptional.isEmpty()) {
                return false;
            }

            User user = userOptional.get();
            // Assign and mark as booked
            appointment.setUser(user);
            appointment.setBooked(true);
            appointmentRepository.save(appointment);
            return true;
        }

        return false;
    }

}
