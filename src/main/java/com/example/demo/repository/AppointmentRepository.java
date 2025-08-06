package com.example.demo.repository;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

   

    // Find all appointments booked by a particular user
    List<Appointment> findByUser(User user);

}
