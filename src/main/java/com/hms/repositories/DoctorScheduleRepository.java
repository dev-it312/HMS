package com.hms.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.entities.Doctor;
import com.hms.entities.DoctorSchedule;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    Optional<DoctorSchedule> findByDoctorIdAndDay(Long doctorId, String day);
    List<DoctorSchedule> findAll(); // Fetch all schedules
}
