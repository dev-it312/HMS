package com.hms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Doctor;
import com.hms.entities.DoctorSchedule;
import com.hms.repositories.DoctorRepository;
import com.hms.repositories.DoctorScheduleRepository;

@Service
public class DoctorScheduleServiceImplementation implements DoctorScheduleService {
    
    @Autowired
    private DoctorScheduleRepository doctorScheduleRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Override
    public boolean assignOrUpdateSchedule(Long doctorId, String day, String fromTime, String toTime) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        
        if (!doctorOptional.isPresent()) {
            return false; // Doctor ID not found
        }
        
        Doctor doctor = doctorOptional.get();
        
        // Check if a schedule already exists for this doctor on the given day
        Optional<DoctorSchedule> existingSchedule = doctorScheduleRepository.findByDoctorIdAndDay(doctorId, day);
        
        if (existingSchedule.isPresent()) {
            // Update existing schedule
            DoctorSchedule schedule = existingSchedule.get();
            schedule.setFromTime(fromTime);
            schedule.setToTime(toTime);
            doctorScheduleRepository.save(schedule);
        } else {
            // Create new schedule
            DoctorSchedule newSchedule = new DoctorSchedule();
            newSchedule.setDoctor(doctor);
            newSchedule.setDay(day);
            newSchedule.setFromTime(fromTime);
            newSchedule.setToTime(toTime);
            doctorScheduleRepository.save(newSchedule);
        }
        
        return true;
    }
    
 // Fetch all doctor schedules
    public List<DoctorSchedule> getAllDoctorSchedules() {
        return doctorScheduleRepository.findAll();
    }
}

