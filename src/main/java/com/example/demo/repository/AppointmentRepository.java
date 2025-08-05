package com.example.demo.repository;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find all available (not booked) appointments ordered by date/time
    List<Appointment> findByBookedFalseOrderByAppointmentDateTimeAsc();

    // Find all appointments booked by a particular user
    List<Appointment> findByUser(User user);

    // Optional: find appointment by date and time (to prevent duplicates)
    boolean existsByAppointmentDateTime(LocalDateTime appointmentDateTime);
}
