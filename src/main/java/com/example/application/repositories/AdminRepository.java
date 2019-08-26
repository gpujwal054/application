package com.example.application.repositories;

import com.example.application.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
     Admin findByAdminEmail(String adminEmail);
}
