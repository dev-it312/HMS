package com.hms.repositories;

import com.hms.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);
    List<Appointment> findByPatientId(Long patientId);
    Optional<Appointment> findByPatientIdAndId(Long patientId, Long id);
}
