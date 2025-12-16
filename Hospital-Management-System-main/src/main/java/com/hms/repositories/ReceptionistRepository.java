package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entities.Receptionist;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {
    Receptionist findByEmail(String email);  // Method to find by email
}
