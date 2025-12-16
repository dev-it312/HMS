package com.hms.services;

import java.util.List;

import com.hms.entities.Doctor;
import com.hms.entities.DoctorSchedule;

public interface DoctorScheduleService {

	boolean assignOrUpdateSchedule(Long doctorId, String day, String fromTime, String toTime);

	List<DoctorSchedule> getAllDoctorSchedules();

}
